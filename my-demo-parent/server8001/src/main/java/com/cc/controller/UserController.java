package com.cc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cc.entity.ResEntity;
import com.cc.entity.UserEntity;
import com.cc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户表
 *
 * @author cc
 * @date 2020-12-29 10:18:49
 */
@RestController
@RequestMapping("/cc/user")
public class UserController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 查询单条
     *
     * @param id
     * @return
     */
    @GetMapping("/info/{id}")
    public ResEntity<UserEntity> info(@PathVariable("id") Long id) throws Exception {
        return ResEntity.success(iUserService.getById(id));
    }

    /**
     * 查询多条
     *
     * @param user
     * @return
     */
    @GetMapping("/list")
    public ResEntity<IPage<UserEntity>> list(UserEntity user) throws Exception {
        return ResEntity.success(iUserService.list(user));
    }

    /**
     * 批量新增或修改
     *
     * @param users
     * @return
     */
    @PostMapping("/register")
    public ResEntity<Boolean> register(@RequestBody List<UserEntity> users) throws Exception {
        return ResEntity.success(iUserService.register(users));
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @PostMapping("/delete")
    public ResEntity<Boolean> delete(@RequestBody List<Long> ids) throws Exception {
        return ResEntity.success(iUserService.removeByIds(ids));
    }

    /**
     * 登录
     *
     * @param userEntity
     * @return
     * @throws Exception
     */
    @PostMapping("/login")
    public ResEntity<String> login(@RequestBody UserEntity userEntity) throws Exception {
        return ResEntity.success(iUserService.login(userEntity));
    }

    /**
     * 登出
     *
     * @param token
     * @return
     * @throws Exception
     */
    @GetMapping("/logout")
    public ResEntity<String> logout(String token) throws Exception {
        redisTemplate.delete(token);
        return ResEntity.success();
    }

    /**
     * 获取当前登录用户
     *
     * @param token
     * @return
     * @throws Exception
     */
    @GetMapping("/getCurrentUser/{token}")
    public ResEntity<UserEntity> getCurrentUser(@PathVariable("token") String token) throws Exception {
        Object currentUser = redisTemplate.opsForValue().get(token);
        return ResEntity.success(currentUser == null ? null : (UserEntity) currentUser);
    }

}
