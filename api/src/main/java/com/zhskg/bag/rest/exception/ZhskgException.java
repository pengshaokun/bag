package com.zhskg.bag.rest.exception;

/**
 * @Author: qiuw
 * @Date: 2018/10/11 10:41
 * @Description: 封装智慧式箱包项目异常
 */
public class ZhskgException extends RuntimeException{

    private Integer code;

    private String message;

    public ZhskgException(ServiceExceptionEnum serviceExceptionEnum) {
        this.code = serviceExceptionEnum.getCode();
        this.message = serviceExceptionEnum.getMessage();
    }

    /**
     *参数校验异常构造方法
     */
    public ZhskgException(ZhskgExceptionEnum serviceExceptionEnum, String message) {
        this.code = serviceExceptionEnum.getCode();
        this.message = message;
    }

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
