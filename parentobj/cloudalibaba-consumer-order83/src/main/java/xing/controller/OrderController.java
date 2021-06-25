package xing.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author DXX
 * @Date 2021/6/25
 **/
@RestController
@Slf4j
@RequestMapping("/order")
public class OrderController {

    @Value("${server.port}")
    private String serverPort;

    private String SERVER =  "http://nacos-payment-prodiver";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/nacos/{id}")
    public String getPayment(@PathVariable("id") Integer id) {
        String result = this.restTemplate.getForObject(SERVER + "/payment/nacos/" + id, String.class);
        return result;
    }
}
