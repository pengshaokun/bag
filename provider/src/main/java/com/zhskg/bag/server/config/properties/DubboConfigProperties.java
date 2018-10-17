package com.zhskg.bag.server.config.properties;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
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

    private Application application;

    private Protocol protocol;

    private Registry registry;

    private String packageName;

    /**--------以下配置仅作为 spring-configuration-metadata自动提示-----------**/

    @Data
    public static class Protocol extends ProtocolConfig{}

    @Data
    public static class Application extends ApplicationConfig{}

    @Data
    public static class Registry extends RegistryConfig{}
}
