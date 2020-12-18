package com.cc.mapper;

import com.cc.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员表
 * 
 * @author cc
 * @email 
 * @date 2020-12-18 10:27:27
 */
@Mapper
public interface MemberMapper extends BaseMapper<MemberEntity> {
	
}
