package com.cc.enums;

/**
 * @创建人 池臣
 * @创建时间 2021/1/12
 * @描述 返回结果状态值
 */
public enum ResCodeEnum {

    /**
     * 成功
     */
    SUCCESS(200),

    /**
     * 失败
     */
    FAILURE(500);

    public Integer code;

    ResCodeEnum(Integer code) {
        this.code = code;
    }
}
