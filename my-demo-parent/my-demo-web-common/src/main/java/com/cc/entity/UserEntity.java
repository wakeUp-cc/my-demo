package com.cc.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 * 
 * @author cc
 * @date 2021-01-13 13:39:25
 */
@Data
@TableName("user")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class UserEntity extends QueryEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * 账号
	 */
	@TableField("username")
	private String username;

	/**
	 * 密码
	 */
	@TableField("password")
	private String password;

	/**
	 * 用户名
	 */
	@TableField("name")
	private String name;

	/**
	 * 头像
	 */
	@TableField("icon")
	private String icon;

	/**
	 * 年龄
	 */
	@TableField("age")
	private Integer age;

	/**
	 * 性别
	 */
	@TableField("sex")
	private Integer sex;

	/**
	 * 注册时间
	 */
	@TableField("create_time")
	private Date createTime;

	/**
	 * 修改时间
	 */
	@TableField("update_time")
	private Date updateTime;

}
