package com.sunyu.k8s.enums;

import com.power.common.interfaces.IMessage;

/**
 * Error code enum
 * @author yu on 2018/12/31.
 */
public enum ErrorCode implements IMessage {

    SUCCESS("0000", "succeed"),

    PARAM_EMPTY("1001", "必选参数为空"),

    PARAM_ERROR("1002", "参数格式错误"),

    UNKNOWN_ERROR("9999", "系统繁忙，请稍后再试....");

    private String code;
    private String message;


    ErrorCode(String errCode, String errMsg) {
        this.code = errCode;
        this.message = errMsg;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
