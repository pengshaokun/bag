package com.zhskg.bag.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhangshengyue
 * @date 2018/10/7
 * desc: 授权返回值
 */
@Data
public class AuthResponse implements Serializable {


    private String token;

    private String phoneNo;

    private Long registerId;

    private String userName;
//
//    private String deviceId;

}
