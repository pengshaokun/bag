package com.zhskg.bag.server.service;

import java.util.Map;

/**
 * @author zhangshengyue
 * @date 2018/10/7
 * desc: 短信
 */
public interface ISmsPushService {



    boolean sendSms(Map<String,Object> param, String phoneNo);

}
