package com.cc.service.impl;

import com.cc.mapper.MemberMapper;
import com.cc.entity.MemberEntity;
import com.cc.service.IMemberService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberMapper, MemberEntity> implements IMemberService {

    @Override
    public IPage<MemberEntity> list(MemberEntity member) throws Exception {
        IPage<MemberEntity> result = new Page<>();
        if (member.getIsPage()) {
            result = page(
                    new Page<MemberEntity>(member.getCurrent(), member.getSize()),
            new QueryWrapper<MemberEntity>()
                    .lambda()
            );
        } else {
            result.setRecords(
                    list(
                            new QueryWrapper<MemberEntity>()
                                    .lambda()
                    )
            );
        }
        return result;
    }

}