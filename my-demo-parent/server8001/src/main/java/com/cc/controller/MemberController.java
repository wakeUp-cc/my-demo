package com.cc.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cc.entity.MemberEntity;
import com.cc.entity.ResEntity;
import com.cc.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * 会员表
 *
 * @author cc
 * @email
 * @date 2020-12-18 17:33:07
 */
@RestController
@RequestMapping("cc/member")
public class MemberController {

    @Autowired
    private MemberService iMemberService;

    /**
     * 获取单条
     */
    @GetMapping("/info/{id}")
    public ResEntity<MemberEntity> info(@PathVariable("id") Long id){
		MemberEntity member = iMemberService.getById(id);
        return ResEntity.success(member);
    }

    /**
     * 查询多条
     */
    @GetMapping("/list")
    public ResEntity<IPage<MemberEntity>> list(MemberEntity member) {
        Page<MemberEntity> result = new Page<>();
        if (member.getIsPage()) {
            result.setRecords(
                        iMemberService.list(
                            new QueryWrapper<MemberEntity>().lambda()
                    )
            );
        } else {
            result = iMemberService.page(
                    new Page<MemberEntity>(member.getCurrent(), member.getSize()),
                    new QueryWrapper<MemberEntity>()
                            .lambda()
            );
        }
        return ResEntity.success(result);
    }

    /**
     * 新增或修改
     */
    @PostMapping("/saveOrUpdate")
    public ResEntity<String> saveOrUpdate(@RequestBody MemberEntity member){
		iMemberService.saveOrUpdate(member);
        return ResEntity.success();
    }

    /**
     * 批量删除
     */
    @PostMapping("/delete")
    public ResEntity<String> delete(@RequestBody Long[] ids){
		iMemberService.removeByIds(Arrays.asList(ids));
        return ResEntity.success();
    }

}
