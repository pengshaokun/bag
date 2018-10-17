package com.zhskg.bag.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class RegisterUser implements Serializable {
    /**
     * 主键
     */
    private Long registerId;

    /**
     * 登陆账号（手机号）
     */
    private String account;

    /**
     * 登陆密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nike;

    /**
     * 头像
     */
    private String head;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 注册时间
     */
    private Date registerTime;

    /**
     * 最后一次登陆时间
     */
    private Date lastloginTime;

    /**
     * 状态0：启用 1：禁用 2：已注销
     */
    private Integer status;

    /**
     * 最近一次登陆设备号（id）
     */
    private String lastLoginDeviceId;

    /**
     * 验证码
     */
    private String code;


}