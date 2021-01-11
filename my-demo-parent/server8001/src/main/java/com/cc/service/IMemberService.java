package com.cc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cc.entity.MemberEntity;

/**
 * 会员表
 *
 * @author cc
 * @date 2021-01-11 16:40:57
 */
public interface IMemberService extends IService<MemberEntity> {

    /**
     * 查询多条
     *
     * @param member
     * @return
     */
    IPage<MemberEntity> list(MemberEntity member) throws Exception;

}

