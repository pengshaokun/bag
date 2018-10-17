package com.zhskg.bag.entity;

import java.util.Date;

public class ReportBag {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 注册用户id
     */
    private Long registerId;

    /**
     * 箱包id
     */
    private Long bagInfoId;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 故障内容
     */
    private String content;

    /**
     * 申请故障详情图路径  
     */
    private String url;

    /**
     * 处理结果
     */
    private String result;

    /**
     * 处理状态：0：未处理 1：处理中 2：已处理
     */
    private Integer status;

    /**
     * 处理人用户id（系统用户id）
     */
    private Long sysUserId;

    /**
     * 申报故障的时间
     */
    private Date declarationTime;

    /**
     * 处理故障的时间
     */
    private Date disposeTime;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(Long sysUserId) {
        this.sysUserId = sysUserId;
    }

    public Date getDeclarationTime() {
        return declarationTime;
    }

    public void setDeclarationTime(Date declarationTime) {
        this.declarationTime = declarationTime;
    }

    public Date getDisposeTime() {
        return disposeTime;
    }

    public void setDisposeTime(Date disposeTime) {
        this.disposeTime = disposeTime;
    }
}