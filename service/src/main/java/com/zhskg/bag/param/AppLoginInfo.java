package com.zhskg.bag.param;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author :jean
 * @date :2018/10/2
 * desc:登录注册
 */
@Data
public class AppLoginInfo implements Serializable {


    /**
     * 手机号
     */
    private String account;
    /**
     * 设备编号
     */
    private String deviceId;

    /**
     * 设备品牌
     */
    private String brand;

    /**
     * 设备型号
     */
    private String model;


    private String code;

    /**
     * 设备类型（1-安卓 2-苹果）
     */
    private Integer type;

    /**
     * 设备登录ip地址
     */
    private String ipAddress;

    /**
     * 登录地维度
     */
    private Double latitude;

    /**
     * 登录地经度
     */
    private Double longitude;

}
