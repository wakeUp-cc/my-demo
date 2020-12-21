package com.shangji;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("io.guli.dao")
public class Generator {
	public static void main(String[] args) {
		SpringApplication.run(Generator.class, args);
	}
}
