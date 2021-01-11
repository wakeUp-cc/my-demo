package com.cc.controller;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cc.entity.MenuEntity;
import com.cc.entity.ResEntity;
import com.cc.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单表
 *
 * @author cc
 * @date 2021-01-11 16:40:57
 */
@RestController
@RequestMapping("/cc/menu")
public class MenuController {

    @Autowired
    private IMenuService iMenuService;

    /**
     * 查询单条
     *
     * @param id
     * @return
     */
    @GetMapping("/info/{id}")
    public ResEntity<MenuEntity> info(@PathVariable("id") Long id) throws Exception {
        return ResEntity.success(iMenuService.getById(id));
    }

    /**
     * 查询多条
     *
     * @param menu
     * @return
     */
    @GetMapping("/list")
    public ResEntity<IPage<MenuEntity>> list(MenuEntity menu) throws Exception {
        return ResEntity.success(iMenuService.list(menu));
    }

    /**
     * 批量新增或修改
     *
     * @param menus
     * @return
     */
    @PostMapping("/saveOrUpdateBatch")
    public ResEntity<Boolean> saveOrUpdateBatch(@RequestBody List<MenuEntity> menus) throws Exception {
        return ResEntity.success(iMenuService.saveOrUpdateBatch(menus));
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @PostMapping("/delete")
    public ResEntity<Boolean> delete(@RequestBody List<Long> ids) throws Exception {
        return ResEntity.success(iMenuService.removeByIds(ids));
    }

    /**
     * 获取用户菜单树
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/getMenuTree")
    public ResEntity<List<Tree<Long>>> getUserMenuTree() throws Exception {
        return ResEntity.success(iMenuService.getUserMenuTree());
    }

}
