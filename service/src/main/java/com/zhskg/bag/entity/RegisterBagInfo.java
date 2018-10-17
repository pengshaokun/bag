package com.zhskg.bag.entity;

import java.util.Date;

public class RegisterBagInfo {
    /**
     * 主键
     */
    private Long id;

    /**
     * 注册用户账号id
     */
    private Long registerId;

    /**
     * 箱包信息id
     */
    private Long bagInfoId;

    /**
     * 授权人账号id  若授权人账号id和注册用户账号id相同则为自有
     */
    private Long authId;

    /**
     * 授权时间
     */
    private Date authTime;

    /**
     * 状态0：有效 1：无效
     */
    private Integer status;

    /**
     * 灯光效果 0：默认跑马灯 0:0:0：用户自定义RGB   注：RGB值和不能大于3*80（十六进制）
     */
    private String light;

    /**
     * 防丢距离设置
     */
    private Double safeDist;

    /**
     * 取消授权时间
     */
    private Date cancelAuthTime;

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

    public Long getBagInfoId() {
        return bagInfoId;
    }

    public void setBagInfoId(Long bagInfoId) {
        this.bagInfoId = bagInfoId;
    }

    public Long getAuthId() {
        return authId;
    }

    public void setAuthId(Long authId) {
        this.authId = authId;
    }

    public Date getAuthTime() {
        return authTime;
    }

    public void setAuthTime(Date authTime) {
        this.authTime = authTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLight() {
        return light;
    }

    public void setLight(String light) {
        this.light = light;
    }

    public Double getSafeDist() {
        return safeDist;
    }

    public void setSafeDist(Double safeDist) {
        this.safeDist = safeDist;
    }

    public Date getCancelAuthTime() {
        return cancelAuthTime;
    }

    public void setCancelAuthTime(Date cancelAuthTime) {
        this.cancelAuthTime = cancelAuthTime;
    }
}