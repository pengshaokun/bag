package com.zhskg.bag.param;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhangshengyue
 * @date 2018/10/7
 * desc:
 */
@Data
public class BagInfoParam implements Serializable {

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




}
