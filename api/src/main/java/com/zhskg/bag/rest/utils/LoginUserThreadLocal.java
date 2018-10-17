package com.zhskg.bag.rest.utils;


import com.zhskg.bag.param.LoginInfo;

public class LoginUserThreadLocal {

    // 单例 ApiMethodParamThreadLocal

    private LoginUserThreadLocal(){}

    private static ThreadLocal<LoginInfo> threadLocal = ThreadLocal
        .withInitial(() -> {
            LoginInfo loginInfo = new LoginInfo();
            loginInfo.setAccount("");
            return loginInfo;
        });

    public static ThreadLocal<LoginInfo> getInstance(){
        return threadLocal;
    }


    public static void setLoginInfo(LoginInfo loginInfo){
        getInstance().set(loginInfo);
    }


    public static LoginInfo getLoginInfo(){
        return threadLocal.get();
    }

}
