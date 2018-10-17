package com.zhskg.bag.rest.exception;

/**
 * @Author: qiuw
 * @Date: 2018/10/11 10:44
 * @Description: 抽象接口 封装智慧式箱包项目异常枚举
 */
public interface ServiceExceptionEnum {

    /**
     * 获取异常编码
     */
    Integer getCode();

    /**
     * 获取异常信息
     */
    String getMessage();
}
