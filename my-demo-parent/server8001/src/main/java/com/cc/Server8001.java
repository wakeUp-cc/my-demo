package com.cc;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @创建人 池臣
 * @创建时间 2020/12/15
 * @描述
 */
@EnableDiscoveryClient
@SpringBootApplication
public class Server8001 {
    public static void main(String[] args) {
        SpringApplication.run(Server8001.class, args);
    }
}
