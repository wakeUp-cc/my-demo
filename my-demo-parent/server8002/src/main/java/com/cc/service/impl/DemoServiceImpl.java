package com.cc.service.impl;

import com.cc.api.IDemoService;
import org.apache.dubbo.config.annotation.Service;

/**
 * @创建人 池臣
 * @创建时间 2021/1/14
 * @描述 dubbo 调用demo
 */
@Service
public class DemoServiceImpl implements IDemoService {

    @Override
    public String hello() {
        return "你好,这是dubbo调用~";
    }

}
