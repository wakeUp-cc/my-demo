package com.cc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @创建人 池臣
 * @创建时间 2020/12/18
 * @描述 返回值
 */
@Data
@AllArgsConstructor
public class ResEntity<T> {

    private static final long serialVersionUID = 1L;

    /**
     * 返回编码
     */
    private Integer code;
    /**
     * 结果值
     */
    private T message;

    /**
     * 成功响应
     *
     * @return
     */
    public static ResEntity<String> success() {
        return new ResEntity<String>(200, "操作成功!");
    }

    /**
     * 成功响应
     *
     * @param message
     * @return
     */
    public static <T> ResEntity<T> success(T message) {
        return new ResEntity<T>(200, message);
    }

    /**
     * 失败响应
     *
     * @return
     */
    public static ResEntity<String> error() {
        return new ResEntity<String>(500, "操作失败!");
    }

    /**
     * 失败响应
     *
     * @param message
     * @return
     */
    public static <T> ResEntity<T> error(T message) {
        return new ResEntity<T>(500, message);
    }

}
