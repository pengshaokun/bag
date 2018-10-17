package com.zhskg.bag.entity;

import java.io.Serializable;
import java.util.Date;

public class BagLocation implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 箱包信息表ID
     */
    private Long bagInfoId;

    /**
     * 纬度
     */
    private Double latitude;

    /**
     * 经度
     */
    private Double longitude;

    /**
     * 上报时间
     */
    private Date reportTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBagInfoId() {
        return bagInfoId;
    }

    public void setBagInfoId(Long bagInfoId) {
        this.bagInfoId = bagInfoId;
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

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }
}