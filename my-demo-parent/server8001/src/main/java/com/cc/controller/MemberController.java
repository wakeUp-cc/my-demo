package com.cc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cc.entity.MemberEntity;
import com.cc.entity.ResEntity;
import com.cc.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会员表
 *
 * @author cc
 * @date 2020-12-22 17:49:28
 */
@RestController
@RequestMapping("/cc/member")
public class MemberController {

    @Autowired
    private IMemberService iMemberService;

    /**
     * 获取单条
     *
     * @param id
     * @return
     */
    @GetMapping("/info/{id}")
    public ResEntity<MemberEntity> info(@PathVariable("id") Long id) throws Exception {
        return ResEntity.success(iMemberService.getById(id));
    }

    /**
     * 查询多条
     *
     * @param member
     * @return
     */
    @GetMapping("/list")
    public ResEntity<IPage<MemberEntity>> list(MemberEntity member) throws Exception {
        return ResEntity.success(iMemberService.list(member));
    }

    /**
     * 新增或修改
     *
     * @param member
     * @return
     */
    @PostMapping("/saveOrUpdate")
    public ResEntity<Boolean> saveOrUpdate(@RequestBody MemberEntity member) throws Exception {
        return ResEntity.success(iMemberService.saveOrUpdate(member));
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @PostMapping("/delete")
    public ResEntity<Boolean> delete(@RequestBody List<Long> ids) throws Exception {
        return ResEntity.success(iMemberService.removeByIds(ids));
    }

}
