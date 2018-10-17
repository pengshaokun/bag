package com.zhskg.bag.param;

import lombok.Data;

/**
 * @author zhangshengyue
 * @date 2018/10/7
 * desc:
 */
@Data
public class LoginInfo {
    /**
     * 手机号
     */
    private String account;

    private String userName;

    private Long registerId;

}
