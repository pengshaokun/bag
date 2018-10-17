package com.zhskg.bag.rest.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.AnnotationBean;
import com.zhskg.bag.rest.config.properties.DubboConfigProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jean
 * @date 2018/10/6
 * desc: dubbo 消费者配置
 */
@Configuration
public class DubboConsumerConfig {


    @Autowired
    private DubboConfigProperties dubboConfigProperties;
    @Bean
    public ApplicationConfig applicationConfig() {
        if (StringUtils.isBlank(dubboConfigProperties.getApplication().getName())){
            throw new NullPointerException("dubboConfigProperties.Application.name is null");
        }
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName(dubboConfigProperties.getApplication().getName());
        return applicationConfig;
    }

    @Bean
    public RegistryConfig registryConfig() {
        if (StringUtils.isBlank(dubboConfigProperties.getRegistry().getAddress())){
            throw new NullPointerException("dubboConfigProperties.Registry.address is null");
        }
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(dubboConfigProperties.getRegistry().getAddress());
        return registryConfig;
    }

    @Bean
    public static AnnotationBean annotationBean(@Value("${dubbo.packageName}") String packageName) {
        if (StringUtils.isBlank(packageName)){
            throw new NullPointerException("dubboConfigProperties.ScanePackage is null");
        }
        AnnotationBean annotationBean = new AnnotationBean();
        annotationBean.setPackage(packageName);
        return annotationBean;
    }

}
