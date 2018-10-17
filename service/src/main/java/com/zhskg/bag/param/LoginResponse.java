package com.zhskg.bag.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @author jean
 * @date 2018/10/7
 * desc: 登录注册返回值
 */
@Data
public class LoginResponse implements Serializable {
    private String token;

    private String phoneNo;
}
