package com.cc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cc.entity.UserEntity;

import java.util.List;

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

    /**
     * 批量新增或修改
     *
     * @param users
     * @return
     * @throws Exception
     */
    Boolean register(List<UserEntity> users) throws Exception;

    /**
     * 登录
     *
     * @param userEntity
     * @return
     * @throws Exception
     */
    String login(UserEntity userEntity) throws Exception;

}

