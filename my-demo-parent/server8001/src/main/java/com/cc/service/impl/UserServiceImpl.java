package com.cc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.entity.ResEntity;
import com.cc.entity.UserEntity;
import com.cc.mapper.UserMapper;
import com.cc.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements IUserService {

    @Override
    public IPage<UserEntity> list(UserEntity user) throws Exception {
        IPage<UserEntity> result = new Page<>();
        if (user.getIsPage()) {
            result = page(
                    new Page<UserEntity>(user.getCurrent(), user.getSize()),
            new QueryWrapper<UserEntity>()
                    .lambda()
            );
        } else {
            result.setRecords(
                    list(
                            new QueryWrapper<UserEntity>()
                                    .lambda()
                    )
            );
        }
        return result;
    }

    @Override
    public Boolean register(List<UserEntity> users) throws Exception {
        return saveOrUpdateBatch(users);
    }

    @Override
    public ResEntity<String> login(UserEntity userEntity) throws Exception {
        return null;
    }

}