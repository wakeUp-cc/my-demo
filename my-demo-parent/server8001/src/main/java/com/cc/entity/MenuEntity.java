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
 * 菜单表
 * 
 * @author cc
 * @date 2021-01-13 13:35:35
 */
@Data
@TableName("menu")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class MenuEntity extends QueryEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * 菜单名称
	 */
	@TableField("name")
	private String name;

	/**
	 * 菜单地址
	 */
	@TableField("path")
	private String path;

	/**
	 * 菜单图标
	 */
	@TableField("icon")
	private String icon;

	/**
	 * 菜单排序
	 */
	@TableField("sort")
	private Integer sort;

	/**
	 * 是否显示   0不显示  1显示
	 */
	@TableField("show_status")
	private Integer showStatus;

	/**
	 * 父菜单ID   0为顶级菜单
	 */
	@TableField("parent_id")
	private Long parentId;

	/**
	 * 创建人员ID
	 */
	@TableField("create_user")
	private Long createUser;

	/**
	 * 创建时间
	 */
	@TableField("create_time")
	private Date createTime;

	/**
	 * 修改人员ID
	 */
	@TableField("update_user")
	private Long updateUser;

	/**
	 * 修改时间
	 */
	@TableField("update_time")
	private Date updateTime;

}
