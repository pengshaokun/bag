package com.zhskg.bag.server.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author jean
 * @date 2018/10/7
 * desc: redis 配置参数
 */
@ConfigurationProperties(prefix = RedisConfigProperties.REDIS_PREFIX)
@Component
public class RedisConfigProperties {

    public static final String REDIS_PREFIX = "redis";




}
