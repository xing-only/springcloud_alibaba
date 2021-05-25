package com.xing;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: TODO
 * @Author DXX
 * @Date 2021/5/13
 **/
public class Demo {

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = sdf.parse("2021-05-25 10:25:10");
        System.out.println(editCertificatesStatus(parse));;
    }

    private static String editCertificatesStatus(Date certificatesDate){
        if(certificatesDate == null){
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat();
        Date now = new Date();
        if(now.after(certificatesDate)){
            return "30";
        }else{
            long day = (certificatesDate.getTime() - now.getTime()) / 1000 / 3600 / 24;
            if(day > 10){
                return "10";
            }else{
                return "20";
            }
        }
    }
}
