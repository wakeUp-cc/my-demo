package com.cc.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 菜单表
 * 
 * @author cc
 * @date 2021-01-11 16:40:57
 */
@Data
@TableName("menu")
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
	private String name;

	/**
	 * 菜单地址
	 */
	private String path;

	/**
	 * 菜单图标
	 */
	private String icon;

	/**
	 * 菜单排序
	 */
	private Integer sort;

	/**
	 * 是否显示   0不显示  1显示
	 */
	private Integer showStatus;

	/**
	 * 父菜单ID   0为顶级菜单
	 */
	private Long parentId;

	/**
	 * 创建人员ID
	 */
	private Long createUser;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 修改人员ID
	 */
	private Long updateUser;

	/**
	 * 修改时间
	 */
	private Date updateTime;

}
