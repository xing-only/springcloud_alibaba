package com.xing;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;

/**
 * @Description: TODO
 * @Author DXX
 * @Date 2021/5/13
 **/
public class Demo {

    public static void main(String[] args) throws ParseException {

//        HashMap<String,Object> map = new HashMap<>();
//        map.put("big",new BigDecimal(12));
//        String big = map.get("big").toString();
//        System.out.println(big);

        String message = "Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown column 'ss' in 'field list'\r\n###";
        message = message.substring(message.indexOf("MySQLSyntaxErrorException")+"MySQLSyntaxErrorException".length()+1, message.indexOf("\r", message.indexOf("MySQLSyntaxErrorException")+1));
        System.out.println(message);

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
