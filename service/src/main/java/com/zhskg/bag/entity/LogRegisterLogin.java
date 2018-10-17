package com.zhskg.bag.entity;

import java.io.Serializable;
import java.util.Date;

public class LogRegisterLogin implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 注册用户账号id
     */
    private Long registerId;

    /**
     * 注册用户登陆设备id
     */
    private Long deviceId;

    /**
     * 登陆设备ip地址
     */
    private String ipAddress;

    /**
     * 登陆地点纬度
     */
    private Double latitude;

    /**
     * 登陆地点经度
     */
    private Double longitude;

    /**
     * 登陆时间
     */
    private Date loginTime;

    /**
     * 登陆状态 0：登陆失败 1：登陆成功
     */
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRegisterId() {
        return registerId;
    }

    public void setRegisterId(Long registerId) {
        this.registerId = registerId;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}