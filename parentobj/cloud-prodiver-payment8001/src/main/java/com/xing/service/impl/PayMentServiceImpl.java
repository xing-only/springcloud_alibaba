package com.xing.service.impl;

import cn.hutool.poi.excel.WorkbookUtil;
import com.xing.dao.PayMentDao;
import com.xing.entity.PayMent;
import com.xing.entity.UserTest;
import com.xing.service.PayMentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.Validate;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ServerErrorException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * @Author DXX
 * @Date 2021/4/16
 **/
@Slf4j
@Service("payMentService")
public class PayMentServiceImpl implements PayMentService {

    @Resource
    private PayMentDao payMentDao;

    @Resource
    private HttpServletResponse response;

    @Override
    public void add(PayMent entity) {
        this.payMentDao.add(entity);
    }

    @Override
    public PayMent getPayMentById(Integer id) {
        PayMent entity = this.payMentDao.getPayMentById(id);
        return entity;
    }
    /**
     * 生成序列号
     *
     * @param platId    平台代码
     * @param seqTypeId 序号类型
     */
    @Override
    @Transactional
    public String generateSequence(String platId, String seqTypeId) {
        Validate.notEmpty(platId, "平台代码不能为空");
        Validate.notEmpty(seqTypeId, "序号类型不能为空");
        String rs = payMentDao.selectGenerateSequence(platId, seqTypeId);
        Validate.notEmpty(rs, "序列号生成失败");
        return rs;
    }

    /**
     * 生成序列号
     *
     * @param platId    平台代码
     * @param seqTypeId 序号类型
     */
    @Override
    @Transactional
    public String call(String platId, String seqTypeId) {
        Validate.notEmpty(platId, "平台代码不能为空");
        Validate.notEmpty(seqTypeId, "序号类型不能为空");
        String seqNum = "";
        Map<String,String> map = new HashMap<>();
        map.put("asPlatId",platId);
        map.put("asSeqTypeId",seqTypeId);
        map.put("seqNum","");
        payMentDao.call(map);
        seqNum = map.get("seqNum");
        return seqNum;
    }

    @Override
    public void export() throws IOException {

        List<UserTest> userList = new ArrayList<>();
        userList.add(new UserTest("1","zhangsan","18","a","b","c","d"));
        userList.add(new UserTest("2","lisi","18","a","b","c","d"));
        userList.add(new UserTest("3","wangwu","18","a","b","c","d"));
        ArrayList<Object> list = new ArrayList<>();
        list.add("编号");
        list.add("姓名");
        list.add("年龄");
        list.add("备注1");
        list.add("备注2");
        list.add("备注3");
        list.add("备注4");

        SXSSFWorkbook contentRow = new SXSSFWorkbook();
        Sheet sheet = contentRow.createSheet("学生表");
        CellStyle topStyle = this.getTopStyle(contentRow);
        CellStyle columnTopStyle = this.getColumnTopStyle(contentRow);
        CellStyle style = this.setStyle(contentRow);
        CellStyle otherStyle = this.getOtherStyle(contentRow);

        //第一行，报表名称
        Row rowm = sheet.createRow(0);
        Cell cell1Title = rowm.createCell(0);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));
        cell1Title.setCellStyle(topStyle);
        cell1Title.setCellValue("学生报表");

        //第二行和第三行为公司
        Row rowm2 = sheet.createRow(1);
        Cell cell1Title2 = rowm2.createCell(0);
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 3));
        cell1Title2.setCellStyle(otherStyle);
        cell1Title2.setCellValue("托运方：成都市上市公司");

        Row rowm3 = sheet.createRow(2);
        Cell cell1Title3 = rowm3.createCell(0);
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 3));
        cell1Title3.setCellStyle(otherStyle);
        cell1Title3.setCellValue("承运方：广安市下是公司");


        //列表头设置
        Row headRow = sheet.createRow(3);
        for (int i = 0; i < list.size(); i++) {
            Cell cell = headRow.createCell(i);
            Object obj = list.get(i);
            cell.setCellValue(obj.toString());
            cell.setCellStyle(columnTopStyle);
        }

        //设置数据到excel中
        AtomicInteger i = new AtomicInteger(4);
        for(UserTest entity : userList){
            int j = 0;
            Row dataRow = sheet.createRow(i.get());

            Cell cell0 = dataRow.createCell(j++);
            cell0.setCellValue(entity.getNo());
            cell0.setCellStyle(style);

            Cell cell1 = dataRow.createCell(j++);
            cell1.setCellValue(entity.getName());
            cell1.setCellStyle(style);

            Cell cell2 = dataRow.createCell(j++);
            cell2.setCellValue(entity.getAge());
            cell2.setCellStyle(style);

            Cell cell3 = dataRow.createCell(j++);
            cell3.setCellValue(entity.getDesc1());
            cell3.setCellStyle(style);

            Cell cell4 = dataRow.createCell(j++);
            cell4.setCellValue(entity.getDesc2());
            cell4.setCellStyle(style);

            Cell cell5 = dataRow.createCell(j++);
            cell5.setCellValue(entity.getDesc3());
            cell5.setCellStyle(style);

            Cell cell6 = dataRow.createCell(j++);
            cell6.setCellValue(entity.getDesc4());
            cell6.setCellStyle(style);

            i.getAndIncrement();
        }

        //数据后的第一行和第二行
        Row rowm4 = sheet.createRow(i.get());
        Cell cell1Title4 = rowm4.createCell(0);
        sheet.addMergedRegion(new CellRangeAddress(i.get(), i.get(), 0, 2));
        cell1Title4.setCellStyle(otherStyle);
        cell1Title4.setCellValue("托运方：");

        Cell cell1Title5 = rowm4.createCell(3);
        sheet.addMergedRegion(new CellRangeAddress(i.get(), i.get(), 3, 6));
        cell1Title5.setCellStyle(otherStyle);
        cell1Title5.setCellValue("承运方：");

        response.setContentType("application/msexcel;charset=UTF-8");
        WorkbookUtil.writeBook(contentRow, response.getOutputStream());
    }

    /*
     * 标题单元格样式
     */
    public CellStyle getTopStyle(SXSSFWorkbook workbook) {

        // 设置字体
        Font font = workbook.createFont();
        //设置字体大小
        font.setFontHeightInPoints((short)11);
        //字体加粗
        font.setBold(true);
        //设置样式;
        CellStyle style = workbook.createCellStyle();
        //设置底边框;
        style.setBorderBottom(BorderStyle.NONE);
        //设置底边框颜色;
        style.setBorderRight(BorderStyle.NONE);
        //设置左边框;
        style.setBorderLeft(BorderStyle.NONE);
        //设置左边框颜色;
        style.setBorderTop(BorderStyle.NONE);
        //在样式用应用设置的字体;
        style.setFont(font);
        //设置自动换行;
        style.setWrapText(false);
        //设置水平对齐的样式为居中对齐;
        style.setAlignment(HorizontalAlignment.CENTER);
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }

    /*
     * 列头单元格样式
     */
    public CellStyle getColumnTopStyle(SXSSFWorkbook workbook) {

        // 设置字体
        Font font = workbook.createFont();
        //设置字体大小
        font.setFontHeightInPoints((short)11);
        //字体加粗
        font.setBold(true);
        //设置样式;
        CellStyle style = workbook.createCellStyle();
        //设置底边框;
        style.setBorderBottom(BorderStyle.THIN);
        //设置底边框颜色;
        style.setBorderRight(BorderStyle.THIN);
        //设置左边框;
        style.setBorderLeft(BorderStyle.THIN);
        //设置左边框颜色;
        style.setBorderTop(BorderStyle.THIN);
        //在样式用应用设置的字体;
        style.setFont(font);
        //设置自动换行;
        style.setWrapText(false);
        //设置水平对齐的样式为居中对齐;
        style.setAlignment(HorizontalAlignment.CENTER);
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        //设置单元格背景颜色
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return style;
    }

    /*
     * 其他单元格样式
     */
    public CellStyle getOtherStyle(SXSSFWorkbook workbook) {

        //设置样式;
        CellStyle style = workbook.createCellStyle();
        //设置底边框;
        style.setBorderBottom(BorderStyle.NONE);
        //设置底边框颜色;
        style.setBorderRight(BorderStyle.NONE);
        //设置左边框;
        style.setBorderLeft(BorderStyle.NONE);
        //设置左边框颜色;
        style.setBorderTop(BorderStyle.NONE);
        //设置自动换行;
        style.setWrapText(false);
        //设置水平对齐的样式为居中对齐;
        style.setAlignment(HorizontalAlignment.LEFT);
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }

    /*
     * 列头单元格样式
     */
    public CellStyle setStyle(SXSSFWorkbook workbook) {

        //设置样式;
        CellStyle style = workbook.createCellStyle();
        //设置底边框;
        style.setBorderBottom(BorderStyle.THIN);
        //设置底边框颜色;
        style.setBorderRight(BorderStyle.THIN);
        //设置左边框;
        style.setBorderLeft(BorderStyle.THIN);
        //设置左边框颜色;
        style.setBorderTop(BorderStyle.THIN);
        //设置自动换行;
        style.setWrapText(false);
        //设置水平对齐的样式为居中对齐;
        style.setAlignment(HorizontalAlignment.CENTER);
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }

}
