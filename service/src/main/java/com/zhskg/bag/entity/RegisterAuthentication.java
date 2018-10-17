package com.zhskg.bag.entity;

import java.util.Date;

public class RegisterAuthentication {
    /**
     * 主键
     */
    private Long id;

    /**
     * 注册用户号（id）
     */
    private Long registerId;

    /**
     * 性别 0：男 1：女
     */
    private Integer sex;

    /**
     * 身份证号码
     */
    private String idNum;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 身份证附件地址（正面）
     */
    private String idFilePos;

    /**
     * 身份证附件地址（背面）
     */
    private String idFileNag;

    /**
     * 认证状态0：未认证 1：认证中 2：认证失败 3：已认证
     */
    private Integer status;

    /**
     * 认证失败原因
     */
    private String reason;

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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getIdFilePos() {
        return idFilePos;
    }

    public void setIdFilePos(String idFilePos) {
        this.idFilePos = idFilePos;
    }

    public String getIdFileNag() {
        return idFileNag;
    }

    public void setIdFileNag(String idFileNag) {
        this.idFileNag = idFileNag;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}