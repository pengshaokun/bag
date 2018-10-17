package com.zhskg.bag.service;

import com.zhskg.bag.enums.SmsProjectName;

/**
 * @author zhangshengyue
 * @date 2018/10/7
 * desc: 短信发送接口
 */
public interface ISmsSendCodeService {

    /**
     * 发送验证码
     * @param phoneNo
     * @param length 验证码长度
     * @return
     */
    Boolean sendCode(String phoneNo,Integer length,SmsProjectName smsProjectName);


}
