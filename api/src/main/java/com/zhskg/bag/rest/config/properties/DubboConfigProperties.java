package com.zhskg.bag.rest.config.properties;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author jean
 * @date 2018/10/6
 * desc:
 */
@ConfigurationProperties(prefix = DubboConfigProperties.DUBBO_PREFIX)
@Component
@Data
public class DubboConfigProperties {


    public static final String DUBBO_PREFIX = "dubbo";

    private String scan;

    private ApplicationConfig application;

    private ProtocolConfig protocol;

    private RegistryConfig registry;

    private String scanePackage;

}
