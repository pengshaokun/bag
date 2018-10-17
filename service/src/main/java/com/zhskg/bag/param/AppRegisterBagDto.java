package com.zhskg.bag.param;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: Qiuw
 * @date: 2018/10/16 14:26
 * @description:
 */
@Data
public class AppRegisterBagDto implements Serializable {

    private static final long serialVersionUID = 1L;

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
}
