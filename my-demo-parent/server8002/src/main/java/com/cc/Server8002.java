package com.cc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @创建人 池臣
 * @创建时间 2020/12/16
 * @描述
 */
@EnableDiscoveryClient
@SpringBootApplication
public class Server8002 {
    public static void main(String[] args) {
        SpringApplication.run(Server8002.class, args);
    }
}