package com.cc.feign;

import com.cc.hystrix.DemoFeignFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @创建人 池臣
 * @创建时间 2021/1/13
 * @描述 demo调用方
 */
@FeignClient(name = "server8002", fallbackFactory = DemoFeignFallback.class)
public interface IDemoFeignClient {

    @GetMapping("/demo1/hello/")
    String hello();

}
