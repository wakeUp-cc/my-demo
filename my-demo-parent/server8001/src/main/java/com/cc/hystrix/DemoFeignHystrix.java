package com.cc.hystrix;

import com.cc.feign.IDemoFeignClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @创建人 池臣
 * @创建时间 2021/1/13
 * @描述 demo熔断
 */
@Component
public class DemoFeignHystrix implements FallbackFactory<IDemoFeignClient> {

    @Override
    public IDemoFeignClient create(Throwable throwable) {
        return new IDemoFeignClient() {
            @Override
            public String hello() {
                return "进入熔断,服务没了~";
            }
        };
    }

}
