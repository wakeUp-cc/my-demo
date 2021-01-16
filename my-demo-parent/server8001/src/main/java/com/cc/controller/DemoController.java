package com.cc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @创建人 池臣
 * @创建时间 2021/1/13
 * @描述
 */
@RestController
@RequestMapping("/demo2")
public class DemoController {

    //@Autowired
    //private IDemoFeignClient iDemoFeignClient;

    //@Reference
    //private IDemoService iDemoService;

    //@NacosValue(value = "${test}", autoRefreshed = true)
    //private String value;

    //@GetMapping("/hello")
    //public String hello() {
    //    return iDemoFeignClient.hello() + value;
    //}

    //@GetMapping("/hello2")
    //@SentinelResource(fallback = "hello2FallBack")
    //public String hello2() {
    //    return iDemoService.hello();
    //}

    public String hello2FallBack() {
        return "进入熔断了~";
    }

}
