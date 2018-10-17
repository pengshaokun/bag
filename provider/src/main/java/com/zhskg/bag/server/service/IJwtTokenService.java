package com.zhskg.bag.server.service;

import com.zhskg.bag.param.JwtParam;
import io.jsonwebtoken.Claims;

/**
 * @author zhangshengyue
 * @date 2018/10/7
 * desc:
 */
public interface IJwtTokenService {


     Claims parseJWT(String jsonWebToken);



     String createJWT(JwtParam jwtParam);
}
