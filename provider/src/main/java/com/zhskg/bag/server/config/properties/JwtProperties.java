package com.zhskg.bag.server.config.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * @author jean
 * @date 2018/10/7
 * desc: jwt 配置
 */
@ConfigurationProperties(prefix = JwtProperties.JWT_PREFIX)
@Data
@Component
public class JwtProperties {

    public static final String JWT_PREFIX = "jwt";


    private String secret;


    private Duration expireTime;

}
