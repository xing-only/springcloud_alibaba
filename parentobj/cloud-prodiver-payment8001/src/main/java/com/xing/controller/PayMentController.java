package com.xing.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xing.common.CommonResult;
import com.xing.entity.PayMent;
import com.xing.entity.Test;
import com.xing.service.PayMentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import sun.net.www.protocol.https.Handler;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.*;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Description:
 * @Author DXX
 * @Date 2021/4/16
 **/
@Slf4j
@RestController
@RequestMapping("/payment")
public class PayMentController {
    private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    @Value("${server.port}")
    private String port;

    @Autowired
    private PayMentService payMentService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Resource
    private HttpServletRequest request;

    private static final String TIME_PATTERN = "yyyyMMddHHmmss";
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_PATTERN);

    /**
     * 新增
     * @date 2021/4/16 17:27
     * @author DXX
     * @param entity
     * @return com.xing.common.CommonResult
     **/
    @PostMapping("/add")
    public CommonResult add(@RequestBody PayMent entity){
        log.info("端口号：" + port);
        this.payMentService.add(entity);
        return CommonResult.createSuccess();
    }

    @GetMapping("/get/{id}")
    public CommonResult<PayMent> getById(@PathVariable("id") Integer id){
        log.info("获取数据id：" + id);
        log.info("端口号：" + port);
        PayMent entity = this.payMentService.getPayMentById(id);
        return new CommonResult<PayMent>(200,"success端口号：" + port,entity);
    }

    /**
     * 服务发现，获取注册中心，注册服务的详细信息
     * @date 2021/4/23 11:02
     * @author DXX
     * @param
     * @return com.xing.common.CommonResult
     **/
    @GetMapping("/getDiscovery")
    public CommonResult getDiscovery(){
        List<String> services = discoveryClient.getServices();
        services.forEach(str ->{
            log.info(str);
        });

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVER");
        instances.forEach(entity ->{
            log.info(entity.getServiceId() + "\t" + entity.getHost() + "\t" + entity.getPort() + "\t" + entity.getUri());
        });

        return new CommonResult(200,"success",discoveryClient);
    }

    /**
     * feign 读取超时
     * @date 2021/5/10 16:38
     * @author DXX
     * @param
     * @return com.xing.common.CommonResult
     **/
    @GetMapping("/feignTimeOUt")
    public CommonResult feignTimeOUt(){
        //业务处理 需要3瞄准
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CommonResult.createSuccess();
    }

    @GetMapping("/create")
    public void create(){
        ExecutorService service = Executors.newFixedThreadPool(100);
        Set<String> list = new HashSet<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++)
        {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    for(int i = 0; i < 10; i++){
                        String s = payMentService.generateSequence("P00001", "A01");
                        list.add(s);
                    }
                }
            });
        }
        service.shutdown();
        while(true){
            if(service.isTerminated()){
                break;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("总共用时：" + (end - start));
        System.out.println(list.size());
    }

    /**
     * 数据校验
     * @date 2021/5/6 16:12
     * @author DXX
     * @param
     * @return void
     **/
    @GetMapping("/validate")
    public void validaTest(){
        Test t = new Test();
        t.setName("hello");
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Test>> constraintViolations = validator.validate(t);
        List<String> messageList = new ArrayList<>();
        for (ConstraintViolation<Test> constraintViolation : constraintViolations) {
            messageList.add(constraintViolation.getMessage());
        }
    }

    /**
     * 供外部接口调用
     * @date 2021/5/6 10:04
     * @author DXX
     * @param map
     * @param
     * @return void
     **/
    @PostMapping(value = "/out_download")
    public void outDownload(@RequestBody Map map) throws IOException {
        //解析map，获取下载图片
        Validate.notNull(map,"参数错误");
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(map));
        JSONObject item = jsonObject.getJSONObject("item");
        String title = item.getString("title");
        JSONArray fields = item.getJSONArray("fields");
        List<Map<String,String>> fileLIst = new ArrayList<>();
        for(int i=0; i<fields.size(); i++){
            JSONObject object = fields.getJSONObject(i);
            if(StringUtils.equals("image",object.getString("type"))){
                //需要下载的图片
                JSONArray values = object.getJSONArray("values");
                for(int j=0; j<values.size(); j++){
                    JSONObject fileObj = values.getJSONObject(j);
                    String fileName = fileObj.getString("name");
                    String fileUrl = fileObj.getJSONObject("link").getString("source");
                    Map<String,String> fileMap = new HashMap<>();
                    fileMap.put("fileName",fileName);
                    fileMap.put("fileUrl",fileUrl);
                    fileLIst.add(fileMap);
                }
            }
        }
        if(CollectionUtil.isNotEmpty(fileLIst)){
            //去重
            Map<String, Map<String, String>> fileMaps = fileLIst.stream().collect(Collectors.groupingBy(maps -> maps.get("fileName"), Collectors.collectingAndThen(Collectors.toList(), value -> value.get(0))));
            String downloadFilename = title + "_" + TIME_FORMATTER.format(LocalDateTime.now());//文件的名称
            String path = request.getSession().getServletContext().getRealPath("/") + File.separator + "temp";
            System.out.println(path);
            File file = new File(path);
            if(!file.exists()){
                file.mkdirs();
            }
            String uploadPath = path + File.separator + downloadFilename + ".zip";
            OutputStream out = new FileOutputStream(uploadPath);
            ZipOutputStream zos = new ZipOutputStream(out);
            InputStream ips = null;

            for(String key : fileMaps.keySet()){
                Map<String,String> fileMap = fileMaps.get(key);
                zos.putNextEntry(new ZipEntry(fileMap.get("fileName")));
                String url = fileMap.get("fileUrl");
                HttpRequest httpRequest = new HttpRequest(url);
                if(url.toLowerCase().startsWith("https")){
                    httpRequest.setUrlHandler(new Handler());
                }
                HttpResponse myResponse = httpRequest.execute();
                //获取body中的流
                ips = myResponse.bodyStream();
                log.info("读取文件流大小" + ips.available());
                int len = 0;
                byte[] buffer = new byte[1024];
                while ((len = ips.read(buffer)) != -1){
                    zos.write(buffer, 0, len);
                }
                zos.closeEntry();
                if (null != ips) {
                    ips.close();
                }
            }
            zos.flush();
            zos.close();
            //上传压缩文件到文件服务器
            File uploadFile = new File(uploadPath);
            if(uploadFile.exists()){
                try {
                    System.out.println("生成成功");
                }finally {
                    //删除临时文件
//                    uploadFile.delete();
                }
            }
        }
    }

}
