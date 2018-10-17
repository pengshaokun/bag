package com.zhskg.bag.server.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * @author jean
 * @date 2018/10/7
 * desc: 短信配置
 */
@ConfigurationProperties(prefix = SmsProperties.SMS_PREFIX)
@Component
@Data
public class SmsProperties {
    public static final String SMS_PREFIX = "sms";


    private String appKey;

    private String appSecret;

    private Duration expireTime;

    private SmsRegister register;



    @Data
    public static class SmsRegister{
        private String template;

        private String sign;
    }
}
