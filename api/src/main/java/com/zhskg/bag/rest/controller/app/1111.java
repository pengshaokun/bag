package com.zhskg.bag.rest.controller.app;

import com.zhskg.bag.param.AppRegisterBagDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther:jean
 * @Date:2018/10/17
 * @Descripsion 用户的箱包信息
 */
@Api(value = "用户的箱包信息接口")
@RequestMapping(value = "app/appUser/bagInfo")
@RestController
public class AppRegisterBagInfoController {


    @PostMapping(value = "auth")
    public Object auth(@RequestBody AppRegisterBagDto appRegisterBagDto){
        return null;
    }








}
