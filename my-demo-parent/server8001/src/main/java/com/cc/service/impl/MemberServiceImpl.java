package com.cc.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.mapper.MemberMapper;
import com.cc.entity.MemberEntity;
import com.cc.service.MemberService;


@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberMapper, MemberEntity> implements MemberService {

}