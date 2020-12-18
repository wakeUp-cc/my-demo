package com.cc.controller;

import java.util.Arrays;
import com.cc.entity.ResEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cc.entity.MemberEntity;
import com.cc.service.MemberService;

/**
 * 会员表
 *
 * @author cc
 * @email 
 * @date 2020-12-18 10:27:27
 */
@RestController
@RequestMapping("cc/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public ResEntity<MemberEntity> info(@PathVariable("id") Long id){
		MemberEntity member = memberService.getById(id);
        return ResEntity.success(member);
    }

    /**
     * 新增或修改
     */
    @PostMapping("/saveOrUpdate")
    public ResEntity<String> saveOrUpdate(@RequestBody MemberEntity member){
		memberService.saveOrUpdate(member);
        return ResEntity.success();
    }

    /**
     * 批量删除
     */
    @PostMapping("/delete")
    public ResEntity<String> delete(@RequestBody Long[] ids){
		memberService.removeByIds(Arrays.asList(ids));
        return ResEntity.success();
    }

}
