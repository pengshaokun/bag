package com.zhskg.bag.server.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.AnnotationBean;
import com.zhskg.bag.server.config.properties.DubboConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jean
 * @date 2018/10/6
 * desc: dubbo配置
 */
@Configuration
public class DubboConfig {


    @Autowired
    private DubboConfigProperties dubboConfigProperties;

    @Bean
    @ConditionalOnMissingBean(AnnotationBean.class)
    public static AnnotationBean annotationBean(@Value("${dubbo.packageName}") String packageName) {
        AnnotationBean annotationBean = new AnnotationBean();
        annotationBean.setPackage(packageName);
        return annotationBean;
    }

    @Bean
    @ConditionalOnMissingBean(ApplicationConfig.class)
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName(dubboConfigProperties.getApplication().getName());
        return applicationConfig;
    }

    @Bean
    @ConditionalOnMissingBean(ProtocolConfig.class)
    public ProtocolConfig protocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName(dubboConfigProperties.getProtocol().getName());
        protocolConfig.setPort(dubboConfigProperties.getProtocol().getPort());
        return protocolConfig;
    }

    @Bean
    @ConditionalOnMissingBean(RegistryConfig.class)
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(dubboConfigProperties.getRegistry().getAddress());
        return registryConfig;
    }


}
