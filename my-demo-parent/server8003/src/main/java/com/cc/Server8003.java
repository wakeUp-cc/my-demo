package com.cc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @创建人 池臣
 * @创建时间 2020/12/16
 * @描述
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.cc")
public class Server8003 {
    public static void main(String[] args) {
        SpringApplication.run(Server8003.class, args);
    }
}
