package com.zhskg.bag.entity;

import java.util.Date;

public class LogSysUserLogin {
    /**
     * 主键
     */
    private Long id;

    /**
     * 系统用户账户id
     */
    private Long sysUserId;

    /**
     * 登陆时间
     */
    private Date loginTime;

    /**
     * 退出登陆时间
     */
    private Date logoutTime;

    /**
     * 登陆时的ip
     */
    private String ipAddress;

    /**
     * 浏览器信息
     */
    private String broserInfo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(Long sysUserId) {
        this.sysUserId = sysUserId;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Date logoutTime) {
        this.logoutTime = logoutTime;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getBroserInfo() {
        return broserInfo;
    }

    public void setBroserInfo(String broserInfo) {
        this.broserInfo = broserInfo;
    }
}