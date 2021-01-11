package com.cc.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cc.entity.MenuEntity;

import java.util.List;

/**
 * 菜单表
 *
 * @author cc
 * @date 2021-01-11 16:40:57
 */
public interface IMenuService extends IService<MenuEntity> {

    /**
     * 查询多条
     *
     * @param menu
     * @return
     */
    IPage<MenuEntity> list(MenuEntity menu) throws Exception;

    /**
     * 获取用户菜单树
     *
     * @return
     */
    List<Tree<Long>> getUserMenuTree();

}

