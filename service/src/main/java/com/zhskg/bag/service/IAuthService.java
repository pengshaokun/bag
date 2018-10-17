package com.zhskg.bag.service;

import com.zhskg.bag.entity.RegisterUser;
import com.zhskg.bag.param.AppLoginInfo;
import com.zhskg.bag.param.AuthRequest;
import com.zhskg.bag.param.AuthResponse;
import com.zhskg.bag.param.LoginResponse;

/**
 * @author jean
 * @date 2018/10/6
 * desc: 验证
 */
public interface IAuthService {


    /**
     * 登录
     * @return
     */
    LoginResponse quickLogin(AppLoginInfo appLoginInfo);

    /**
     * 注册
     */
    RegisterUser Register(AppLoginInfo appLoginInfo);

    /**
     * 授权认证
     * @return
     */
    AuthResponse auth(String token);

    /**
     * 获取用户基本信息
     * @return
     */
    RegisterUser getBasicInfo(Long registerId);

    /**
     * 设置基本信息
     * @param registerUser
     * @return
     */
    Boolean setBasicInfo(RegisterUser registerUser);

    /**
     * email查重
     * @param email
     * @return
     */
    Boolean isExistByEmail(String email);

    /**
     * 手机号查重
     * @param phoneNo
     * @return
     */
    Boolean isExistByPhoneNo(String phoneNo);


}
