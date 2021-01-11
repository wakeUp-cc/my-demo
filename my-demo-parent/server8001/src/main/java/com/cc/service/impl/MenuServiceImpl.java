package com.cc.service.impl;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.entity.MenuEntity;
import com.cc.mapper.MenuMapper;
import com.cc.service.IMenuService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, MenuEntity> implements IMenuService {

    @Override
    public IPage<MenuEntity> list(MenuEntity menu) throws Exception {
        IPage<MenuEntity> result = new Page<>();
        if (menu.getIsPage()) {
            result = page(
                    new Page<MenuEntity>(menu.getCurrent(), menu.getSize()),
                    new QueryWrapper<MenuEntity>()
                            .lambda()
            );
        } else {
            result.setRecords(
                    list(
                            new QueryWrapper<MenuEntity>()
                                    .lambda()
                    )
            );
        }
        return result;
    }

    @Override
    public List<Tree<Long>> getUserMenuTree() {
        return TreeUtil.build(list(new QueryWrapper<MenuEntity>())
                        .stream()
                        .map(menu ->
                                new TreeNode<Long>(menu.getId(), menu.getParentId(), menu.getName(), menu.getSort()) {{
                                    setExtra(new HashMap<String, Object>() {{
                                        put("path", menu.getPath());
                                        put("icon", menu.getIcon());
                                        put("sort", menu.getSort());
                                        put("showStatus", menu.getShowStatus());
                                    }});
                                }})
                        .collect(Collectors.toList()),
                0L);
    }

}