package com.cc.service.impl;

import com.cc.mapper.UserMapper;
import com.cc.entity.UserEntity;
import com.cc.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}