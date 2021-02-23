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
 * 会员表
 * 
 * @author cc
 * @date 2021-01-13 13:39:25
 */
@Data
@TableName("member")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class MemberEntity extends QueryEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * 会员名称
	 */
	@TableField("name")
	private String name;

	/**
	 * 会员年龄
	 */
	@TableField("age")
	private Integer age;

	/**
	 * 会员性别  字典表 sex
	 */
	@TableField("sex")
	private Integer sex;

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
