package com.cc.controller;

import com.cc.entity.ResEntity;
import com.cc.entity.MemberEntity;
import com.cc.service.IMemberService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会员表
 *
 * @author cc
 * @date 2021-01-11 16:40:57
 */
@RestController
@RequestMapping("/cc/member")
public class MemberController {

    @Autowired
    private IMemberService iMemberService;

    /**
     * 查询单条
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
     * 批量新增或修改
     *
     * @param members
     * @return
     */
    @PostMapping("/saveOrUpdateBatch")
    public ResEntity<Boolean> saveOrUpdateBatch(@RequestBody List<MemberEntity> members) throws Exception {
        return ResEntity.success(iMemberService.saveOrUpdateBatch(members));
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
