package com.cc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cc.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户表
 * 
 * @author cc
 * @date 2020-12-29 10:18:49
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
	
}
