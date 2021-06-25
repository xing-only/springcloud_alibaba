package xing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description:
 * @Author DXX
 * @Date 2021/6/25
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class NacosPayment9002 {
    public static void main(String[] args){
        SpringApplication.run(NacosPayment9002.class,args);
    }
}
