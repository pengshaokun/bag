package com.zhsbag.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zhsbag.IBaseTest;
import com.zhskg.bag.enums.SmsProjectName;
import com.zhskg.bag.server.dubbo.SmsSendCodeServiceImpl;
import com.zhskg.bag.server.service.ISmsPushService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author jean
 * @date 2018/10/7
 * desc: 短信测试
 */

public class SmsPushTest extends IBaseTest{


    @Reference(version = "")
    private SmsSendCodeServiceImpl smsSendCodeServiceImpl;
    @Test
    public void testSendSms(){

        Boolean aBoolean = smsSendCodeServiceImpl.sendCode("15313883301", 2, SmsProjectName.BAG_APP);
        System.out.println(aBoolean);


    }



}
