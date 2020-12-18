package com.cc.entity;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableField;

import java.util.Date;

/**
 * @创建人 池臣
 * @创建时间 2020/12/18
 * @描述 查询通用条件
 */
@Data
public class QueryEntity {

    /**
     * 开始时间
     */
    @TableField(exist = false)
    private Date startTime;
    /**
     * 结束时间
     */
    @TableField(exist = false)
    private Date endTime;
    /**
     * 当前页
     */
    @TableField(exist = false)
    private Long current = 1L;
    /**
     * 每页记录数
     */
    @TableField(exist = false)
    private Long size = 10L;
    /**
     * 查询内容
     */
    @TableField(exist = false)
    private String search;
    /**
     * 是否分页
     */
    @TableField(exist = false)
    private Boolean isPage = false;

}
