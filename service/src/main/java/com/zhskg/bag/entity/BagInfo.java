package com.zhskg.bag.entity;

import java.io.Serializable;
import java.util.Date;

public class BagInfo implements Serializable{
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long bagInfoId;

    /**
     * 箱包ID
     */
    private String bagId;

    /**
     * 出厂编号
     */
    private String factoryNo;

    /**
     * 箱包名称
     */
    private String productName;

    /**
     * 蓝牙mac地址
     */
    private String bleMac;

    /**
     * 箱包状态：0：正常 1：挂失中 2：已丢失
     */
    private Integer status;

    /**
     * 添加设备时间
     */
    private Date addTime;

    /**
     * 挂失时间
     */
    private Date lostTime;

    /**
     * 当前使用用户账号id
     */
    private Long currentUserId;

    /**
     * 传输密码
     */
    private String connPassword;

    public Long getBagInfoId() {
        return bagInfoId;
    }

    public void setBagInfoId(Long bagInfoId) {
        this.bagInfoId = bagInfoId;
    }

    public String getBagId() {
        return bagId;
    }

    public void setBagId(String bagId) {
        this.bagId = bagId;
    }

    public String getFactoryNo() {
        return factoryNo;
    }

    public void setFactoryNo(String factoryNo) {
        this.factoryNo = factoryNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBleMac() {
        return bleMac;
    }

    public void setBleMac(String bleMac) {
        this.bleMac = bleMac;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getLostTime() {
        return lostTime;
    }

    public void setLostTime(Date lostTime) {
        this.lostTime = lostTime;
    }

    public Long getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(Long currentUserId) {
        this.currentUserId = currentUserId;
    }

    public String getConnPassword() {
        return connPassword;
    }

    public void setConnPassword(String connPassword) {
        this.connPassword = connPassword;
    }
}