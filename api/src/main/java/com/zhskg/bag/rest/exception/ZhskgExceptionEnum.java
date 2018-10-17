package com.zhskg.bag.rest.exception;

/**
 * @Author: qiuw
 * @Date: 2018/10/11  10:45
 * @Description: 自定义异常枚举
 */

public enum ZhskgExceptionEnum implements ServiceExceptionEnum{

    /**
     * 常见异常
     */
    REQUEST_NULL(400, "请求有错误"),
    AUTHEN_ERROR(405, "登录信息异常"),
    SESSION_TIMEOUT(400, "会话超时"),
    SERVER_ERROR(500, "服务器异常"),
    PARAM_VALID_ERROR(408, "参数校验异常"),
    BAG_EXIST(401,"箱包已被绑定");

    private Integer code;

    private String message;

    ZhskgExceptionEnum(int code, String message){
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
