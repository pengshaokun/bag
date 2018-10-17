package com.zhskg.bag.server.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.zhskg.bag.common.util.DateUtil;
import com.zhskg.bag.entity.LogRegisterLogin;
import com.zhskg.bag.entity.RegisterUser;
import com.zhskg.bag.entity.RegisterUserDevice;
import com.zhskg.bag.enums.SmsProjectName;
import com.zhskg.bag.param.*;
import com.zhskg.bag.server.constans.Constans;
import com.zhskg.bag.server.mapper.LogRegisterLoginMapper;
import com.zhskg.bag.server.mapper.RegisterUserDeviceMapper;
import com.zhskg.bag.server.mapper.RegisterUserMapper;
import com.zhskg.bag.server.service.IJwtTokenService;
import com.zhskg.bag.server.service.IRedisTemplateService;
import com.zhskg.bag.service.IAuthService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author jean
 * @date 2018/10/6
 * desc: 验证实现
 */
@Service(version = "1.0")
@Slf4j
public class AuthServiceImpl implements IAuthService {


    @Autowired
    private IJwtTokenService jwtTokenService;

    @Autowired
    private IRedisTemplateService redisTemplateService;

    @Autowired
    private RegisterUserMapper registerUserMapper;

    @Autowired
    private RegisterUserDeviceMapper registerUserDeviceMapper;

    @Autowired
    private LogRegisterLoginMapper logRegisterLoginMapper;

    @Override
    public LoginResponse quickLogin(AppLoginInfo appLoginInfo) {
        String phoneNo = appLoginInfo.getAccount();
        LoginResponse loginResponse = new LoginResponse();
        JwtParam jwtParam = new JwtParam();
        Map<Object, Object> loginMap = redisTemplateService.hashGetAll(phoneNo);
        String code = redisTemplateService.stringGetStringByKey("VerificationCode:" + SmsProjectName.BAG_APP.getDesc() + ":" + phoneNo);
        if (StringUtils.isNotBlank(appLoginInfo.getCode()) && appLoginInfo.getCode().equals(code)) {
            if (MapUtils.isEmpty(loginMap)) {
                //注册
                RegisterUser register = Register(appLoginInfo);
                if (register != null) {
                    jwtParam.setRegisterId(register.getRegisterId());
                }
            } else {
                //登录
                RegisterUser registerUser = new RegisterUser();
                BeanUtils.copyProperties(appLoginInfo, registerUser);
                registerUser.setRegisterId((Long) loginMap.get("registerId"));
                registerUser.setLastloginTime(DateUtil.getDateSysTime());
                registerUser.setLastLoginDeviceId(appLoginInfo.getDeviceId());
                int i = registerUserMapper.updateByPrimaryKeySelective(registerUser);

                RegisterUserDevice registerUserDevice = new RegisterUserDevice();
                registerUserDevice.setRegisterId(registerUser.getRegisterId());
                BeanUtils.copyProperties(appLoginInfo, registerUserDevice);
                registerUserDevice.setDeviceSerialNo(appLoginInfo.getDeviceId());
                int i1 = registerUserDeviceMapper.updateByPrimaryKeySelective(registerUserDevice);

                LogRegisterLogin logRegisterLogin = new LogRegisterLogin();
                logRegisterLogin.setRegisterId(registerUser.getRegisterId());
                logRegisterLogin.setDeviceId(registerUserDevice.getDeviceId());
                BeanUtils.copyProperties(appLoginInfo, logRegisterLogin);
                logRegisterLogin.setLoginTime(DateUtil.getDateSysTime());
                int i2 = logRegisterLoginMapper.insertSelective(logRegisterLogin);

                if (i > 0 && i1 > 0 && i2 > 0) {
                    jwtParam.setRegisterId((Long) loginMap.get("registerId"));
                }
            }

            jwtParam.setAccount(phoneNo);
            jwtParam.setStatus(0);
            String jwt = jwtTokenService.createJWT(jwtParam);
            loginResponse.setToken(jwt);
            loginResponse.setPhoneNo(phoneNo);
//        Object code1 = loginMap.get(Constans.SMSContans.CODE);
            //存redis
            HashMap<Object, Object> map = new HashMap<>();
            BeanUtils.copyProperties(loginResponse, map);
            redisTemplateService.hashPushHashMap(phoneNo, map, map);
        }
        return loginResponse;
    }

    @Override
    public RegisterUser Register(AppLoginInfo appLoginInfo) {

        try {
            RegisterUser registerUser = new RegisterUser();
            Date dateSysTime = DateUtil.getDateSysTime();
            registerUser.setLastloginTime(dateSysTime);
            registerUser.setRegisterTime(dateSysTime);
            BeanUtils.copyProperties(appLoginInfo, registerUser);
            int i = registerUserMapper.insertSelective(registerUser);

            RegisterUserDevice registerUserDevice = new RegisterUserDevice();
            registerUserDevice.setRegisterId(registerUser.getRegisterId());
            BeanUtils.copyProperties(appLoginInfo, registerUserDevice);
            int i1 = registerUserDeviceMapper.insertSelective(registerUserDevice);

            LogRegisterLogin logRegisterLogin = new LogRegisterLogin();
            logRegisterLogin.setRegisterId(registerUser.getRegisterId());
            logRegisterLogin.setDeviceId(registerUserDevice.getDeviceId());
            BeanUtils.copyProperties(appLoginInfo, logRegisterLogin);
            logRegisterLogin.setLoginTime(DateUtil.getDateSysTime());
            int i2 = logRegisterLoginMapper.insertSelective(logRegisterLogin);

            if (i > 0 && i1 > 0 && i2 > 0) {
                return registerUser;
            }
        } catch (BeansException e) {
            log.error("register userInfo insert false", e);
        }
        return null;
    }


    @Override
    public AuthResponse auth(String token) {
        AuthResponse authResponse = new AuthResponse();
        Claims claims = jwtTokenService.parseJWT(token);
        if (Objects.isNull(claims)) {
            log.info("token 认证失败", token);

            return null;
        }
        String phoneNo = (String) claims.get("phoneNo");
        if (StringUtils.isBlank(phoneNo)){
            return null;
        }

        System.out.println(claims.getSubject());
        authResponse.setPhoneNo(phoneNo);
        //redis 验证 目的： 解决 jwt 无法控制时间的问题
        Map<Object, Object> objectObjectMap = redisTemplateService.hashGetAll(phoneNo);
        if (MapUtils.isEmpty(objectObjectMap)){
            log.error("jwt token redis 不存在 已失效");
            return null;
        }
        authResponse.setToken(token);
        authResponse.setRegisterId((Long) claims.get("registerId"));
        return authResponse;
    }

    @Override
    public RegisterUser getBasicInfo(Long registerId) {
        return registerUserMapper.selectByPrimaryKey(registerId);
    }

    @Override
    public Boolean setBasicInfo(RegisterUser registerUser) {
        try {
            int num = registerUserMapper.updateByPrimaryKeySelective(registerUser);
            if (num > 0) {
                return true;
            }
        } catch (Exception e) {
            log.error("BasicInfo update false", e);
        }
        return false;
    }

    @Override
    public Boolean isExistByEmail(String email) {
        Integer num = registerUserMapper.checkEmail(email);
        if (num > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean isExistByPhoneNo(String phoneNo) {
        Integer num = registerUserMapper.checkPhoneNo(phoneNo);
        if (num > 0) {
            return true;
        } else {
            return false;
        }
    }

}
