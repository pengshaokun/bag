package com.zhsbag.service;

import com.zhsbag.IBaseTest;
import com.zhskg.bag.param.JwtParam;
import com.zhskg.bag.server.service.IJwtTokenService;
import io.jsonwebtoken.Claims;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author jean
 * @date 2018/10/7
 * desc:
 */
public class JwtServiceTest extends IBaseTest{

    @Autowired
    private IJwtTokenService jwtTokenService;
    @Test
    public void test(){
        JwtParam jwtParam = new JwtParam();
//        jwtParam.setUserId(12L);
        jwtParam.setUserName("zhang");
        jwtParam.setEmail("zhangshengyue");

        String jwt = jwtTokenService.createJWT(jwtParam);
        Claims claims = jwtTokenService.parseJWT(jwt);
        System.out.println(claims);
    }
}
