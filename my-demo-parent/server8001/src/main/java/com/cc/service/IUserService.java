package com.cc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cc.entity.UserEntity;

/**
 * 用户表
 *
 * @author cc
 * @date 2020-12-29 10:18:49
 */
public interface IUserService extends IService<UserEntity> {

    /**
     * 查询多条
     *
     * @param user
     * @return
     */
    IPage<UserEntity> list(UserEntity user) throws Exception;

}

