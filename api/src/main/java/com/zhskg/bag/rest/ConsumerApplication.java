package com.zhskg.bag.rest;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zhskg.bag.enums.SmsProjectName;
import com.zhskg.bag.rest.utils.ApplicationContextHolder;
import com.zhskg.bag.service.ISmsSendCodeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication(scanBasePackages = "com.zhskg.bag.rest.controller.app")
@ComponentScan("com.zhskg.bag.*")
@EnableConfigurationProperties()
public class ConsumerApplication {



	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);

		System.out.println("cousumer: 启动！");



	}



}


