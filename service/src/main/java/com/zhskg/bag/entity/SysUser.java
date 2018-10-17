package com.zhskg.bag.entity;

import java.util.Date;

public class SysUser {
    /**
     * 主键
     */
    private Long sysUserId;

    /**
     * 系统用户账户（手机号码）
     */
    private String phoneNo;

    /**
     * 系统用户密码
     */
    private String password;

    /**
     * 用户昵称
     */
    private String nick;

    /**
     * 用户头像
     */
    private String head;

    /**
     * 账户添加时间
     */
    private Date addTime;

    /**
     * 最近一次登陆时间
     */
    private Date lostLoginTime;

    /**
     * 0：已启用 1：已禁用 2：已注销
     */
    private Integer status;

    public Long getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(Long sysUserId) {
        this.sysUserId = sysUserId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getLostLoginTime() {
        return lostLoginTime;
    }

    public void setLostLoginTime(Date lostLoginTime) {
        this.lostLoginTime = lostLoginTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}