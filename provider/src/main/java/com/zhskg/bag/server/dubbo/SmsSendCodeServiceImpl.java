package com.zhskg.bag.server.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.google.common.collect.Maps;
import com.zhskg.bag.enums.SmsProjectName;
import com.zhskg.bag.server.config.properties.SmsProperties;
import com.zhskg.bag.server.constans.Constans;
import com.zhskg.bag.server.service.IRedisTemplateService;
import com.zhskg.bag.server.service.ISmsPushService;
import com.zhskg.bag.service.ISmsSendCodeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.temporal.TemporalUnit;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author jean
 * @date 2018/10/7
 * desc: 短信验证码
 */
@Service(version = "1.0")
@Slf4j
public class SmsSendCodeServiceImpl implements ISmsSendCodeService {

    @Autowired
    private IRedisTemplateService redisTemplateService;

    @Autowired
    private SmsProperties smsProperties;

    @Autowired
    private ISmsPushService smsPushService;

    @Override
    public Boolean sendCode(String phoneNo, Integer length, SmsProjectName smsProjectName) {
        String code = RandomStringUtils.randomNumeric(length);
        Map<String, Object> map = Maps.newHashMap();
        map.put("code", code);
        map.put("handleType", "身份");
        map.put("duration", String.valueOf(smsProperties.getExpireTime().toMinutes()));
        boolean sendSuccess = smsPushService.sendSms(map, phoneNo);
        if (sendSuccess) {
            log.info("发送短信成功 phoneNo:{}", phoneNo);
//            Map<String,Object> redisMap = Maps.newHashMap();
//            redisMap.put(Constans.SMSContans.PROJECT_NAME,smsProjectName.name());
//            redisMap.put(Constans.SMSContans.CODE,code);

           redisTemplateService.stringSetValueAndExpireTime("VerificationCode:" + smsProjectName.name()+":" + phoneNo, code, smsProperties.getExpireTime().getSeconds(), TimeUnit.SECONDS);

        }
        return sendSuccess;
    }


}
