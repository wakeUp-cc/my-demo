package com.cc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @创建人 池臣
 * @创建时间 2021/1/13
 * @描述 8002提供方
 */
@RestController
@RequestMapping("/demo1")
public class DemoController {

    @GetMapping("/hello")
    public String hello() {
        return "你好我是8002";
    }

}
