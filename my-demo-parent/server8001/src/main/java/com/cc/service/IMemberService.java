package com.cc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cc.entity.MemberEntity;

/**
 * 会员表
 *
 * @author cc
 * @date 2020-12-22 17:49:28
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

