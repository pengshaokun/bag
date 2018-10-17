package com.zhskg.bag.server.service.impl;

import com.zhskg.bag.common.util.MapUtil;
import com.zhskg.bag.param.JwtParam;
import com.zhskg.bag.param.LoginInfo;
import com.zhskg.bag.server.config.properties.JwtProperties;
import com.zhskg.bag.server.service.IJwtTokenService;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;

/**
 * @author jean
 */
@Slf4j
@Service
public class JwtTokenServiceImpl implements IJwtTokenService{


    @Autowired
    private JwtProperties jwtProperties;


    @Override
    public Claims parseJWT(String jsonWebToken) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(jwtProperties.getSecret()))
                    .parseClaimsJws(jsonWebToken).getBody();
            JwtParam jwtParam = new JwtParam();

            return claims;
        } catch (ExpiredJwtException ex) {
            log.error("token已失效", ex);
            return null;
        } catch (SignatureException se) {
            log.error("签名错误", se);
            return null;
        } catch (Exception e) {
            log.error("解析异常");
            return null;
        }
    }



    @Override
    public String createJWT(JwtParam jwtParam) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(jwtProperties.getSecret());
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        HashMap<String, Object> stringObjectHashMap;
        try {
            stringObjectHashMap = MapUtil.convertToMap(jwtParam);
        } catch (IllegalAccessException e) {
           log.error("对象转map 失败");
           return null;
        }
        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                .addClaims(stringObjectHashMap)
                .signWith(signatureAlgorithm, signingKey);
        //添加Token过期时间
        if (jwtProperties.getExpireTime().getSeconds() >= 0) {
            long expMillis = nowMillis + jwtProperties.getExpireTime().getSeconds();
            Date exp = new Date(expMillis);
            builder.setExpiration(exp).setNotBefore(now);
        }

        //生成JWT
        return builder.compact();
    }


}
