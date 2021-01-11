package com.cc.mapper;

import com.cc.entity.MenuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 菜单表
 * 
 * @author cc
 * @date 2021-01-11 16:40:57
 */
@Mapper
public interface MenuMapper extends BaseMapper<MenuEntity> {
	
}
