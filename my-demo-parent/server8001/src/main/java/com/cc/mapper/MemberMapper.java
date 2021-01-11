package com.cc.mapper;

import com.cc.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员表
 * 
 * @author cc
 * @date 2021-01-11 16:40:57
 */
@Mapper
public interface MemberMapper extends BaseMapper<MemberEntity> {
	
}
