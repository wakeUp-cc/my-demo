package com.cc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员表
 * 
 * @author cc
 * @date 2020-12-21 15:16:24
 */
@Data
@TableName("member")
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
	private String name;
	/**
	 * 会员年龄
	 */
	private Integer age;
	/**
	 * 会员性别  字典表 sex
	 */
	private Integer sex;
	/**
	 * 登记时间
	 */
	private Date createTime;
	/**
	 * 登记人
	 */
	private String createUser;

}
