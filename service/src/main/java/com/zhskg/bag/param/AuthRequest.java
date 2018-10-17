package com.zhskg.bag.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhangshengyue
 * @date 2018/10/7
 * desc: 授权认证请求
 */
@Data
public class AuthRequest implements Serializable{

    private String phoneNo;

    /**
     *
     */
    private String token;


}
