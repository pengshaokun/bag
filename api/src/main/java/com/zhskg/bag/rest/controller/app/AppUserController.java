package com.zhskg.bag.rest.controller.app;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.sun.xml.internal.bind.v2.model.annotation.Quick;
import com.sun.xml.internal.ws.wsdl.writer.document.ParamType;
import com.zhskg.bag.common.util.MapUtil;
import com.zhskg.bag.common.util.RegexValidateUtil;
import com.zhskg.bag.common.util.ReturnMap;
import com.zhskg.bag.entity.RegisterUser;
import com.zhskg.bag.enums.FileSourceEnum;
import com.zhskg.bag.enums.SmsProjectName;
import com.zhskg.bag.param.*;
import com.zhskg.bag.rest.intercepter.AllowAnonymous;
import com.zhskg.bag.rest.utils.ApplicationContextHolder;
import com.zhskg.bag.rest.utils.FileConsumer;
import com.zhskg.bag.rest.utils.LoginUserThreadLocal;
import com.zhskg.bag.service.IAuthService;
import com.zhskg.bag.service.IRedisService;
import com.zhskg.bag.service.ISmsSendCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jean
 * @date 2018/10/7
 * desc:用户信息
 */

@Api(value = "用户接口")
@Slf4j
@RestController
@RequestMapping("/app/userInfo/")
public class AppUserController {

    @Reference(version = "1.0")
    private ISmsSendCodeService smsSendCodeService;

    @Reference(version = "1.0", timeout = 600000)
    private IAuthService authService;

    @Reference(version = "1.0")
    private IRedisService redisService;

    @Autowired
    private FileConsumer fileConsumer;

    private final Integer len = 6;

    @ApiOperation(value = "发送手机验证码", notes = "发送手机验证码")
    @AllowAnonymous
    @PostMapping(value = "sendCode")
    @ResponseBody
    public Object sendCode(@RequestBody RegisterUser registerUser) {

        String phoneNo = registerUser.getAccount();
        if (StringUtils.isEmpty(phoneNo)) {
            return ReturnMap.result(1, "手机号不能空！");
        }
        if (!RegexValidateUtil.checkMobileNumber(phoneNo)) {
            return ReturnMap.result(1, "输入的手机号码格式不正确");
        }
        String code = redisService.stringGetStringByKey("VerificationCode:" + SmsProjectName.BAG_APP.getDesc() + ":" + phoneNo);
        if (StringUtils.isNotEmpty(code)) {
            Long expire = redisService.getExpire("VerificationCode:" + SmsProjectName.BAG_APP.getDesc() + ":" + phoneNo);
            if (expire > 4 * 60) {
                return ReturnMap.result(1, "请不要频繁发送！");
            }
        }
        Boolean flag = smsSendCodeService.sendCode(phoneNo, len, SmsProjectName.BAG_APP);
        if (flag) {
            return ReturnMap.result(0, "发送成功！");
        } else {
            log.error("发送验证码失败");
            return ReturnMap.result(1, "发送失败！");
        }
    }

    @ApiOperation(value = "快捷登录", notes = "快捷登录")
    @AllowAnonymous
    @PostMapping("login/quick")
    @ResponseBody
    public Object quickLogin(@RequestBody AppLoginInfo loginInfo) {

        String account = loginInfo.getAccount();
        String code = loginInfo.getCode();
        //验证手机号码
        if (StringUtils.isEmpty(account) || StringUtils.isEmpty(code)) {
            ReturnMap.result(1, "手机号、验证码都不能为空");
        }
        if (!RegexValidateUtil.checkMobileNumber(account)) {
            return ReturnMap.result(1, "输入的手机格式不正确");
        }

        LoginResponse loginResponse = authService.quickLogin(loginInfo);
        if (loginResponse != null) {
            return ReturnMap.result(0, "登录成功", loginInfo);
        } else {
            return ReturnMap.result(1, "登录失败");
        }
    }

    @ApiOperation(value = "获取用户基本信息", notes = "用户基本信息的获取")
    @GetMapping("getBasicInfo")
    @ResponseBody
    public Object getBasicInfo() {

        try {
            Long registerId = LoginUserThreadLocal.getLoginInfo().getRegisterId();
            RegisterUser basicInfo = authService.getBasicInfo(registerId);
            return ReturnMap.result(0, "获取成功", basicInfo);
        } catch (Exception e) {
            log.error("获取用户基本信息失败", e);
            return ReturnMap.result(-1, "获取失败");
        }
    }

    @ApiOperation(value = "设置用户基本信息", notes = "设置用户基本信息，例如修改昵称、邮箱")
    @PostMapping("setBasicInfo")
    @ResponseBody
    public Object setBasicInfo(@RequestBody RegisterUser registerUser) {

        Long registerId = LoginUserThreadLocal.getLoginInfo().getRegisterId();
        registerUser.setRegisterId(registerId);
        String email = registerUser.getEmail();
        if (email != null) {
            boolean checkEmail = RegexValidateUtil.checkEmail(email);
            if (!checkEmail) {
                return ReturnMap.result(1, "请输入正确的邮箱！");
            }
            Boolean exist = authService.isExistByEmail(email);
            if (exist) {
                return ReturnMap.result(1, "此邮箱已被使用，请换一个邮箱");
            }
        }
        Boolean setBasicInfo = authService.setBasicInfo(registerUser);
        if (setBasicInfo) {
            return ReturnMap.result(0, "保存成功!");
        } else {

            return ReturnMap.result(1, "保存失败！");
        }
    }

    @ApiOperation(value = "设置用户头像")
    @PostMapping(value = "setHeadImage")
    @ResponseBody
    public Object setHead(@RequestParam(value = "file", required = false) MultipartFile file) {

        if (file == null) {
            return ReturnMap.result(1, "头像为空");
        }
        Long registerId = LoginUserThreadLocal.getLoginInfo().getRegisterId();
        FileInfo fileToDisk = fileConsumer.uploadFileToDisk(file, FileSourceEnum.APP_HEAD_IMG.getName());
        if(fileToDisk==null){
            return ReturnMap.result(1, "上传图片失败");
        }
        String filePath = fileToDisk.getFilePath();
        RegisterUser registerUser = new RegisterUser();
        registerUser.setRegisterId(registerId);
        registerUser.setHead(filePath);
        Boolean setBasicInfo = authService.setBasicInfo(registerUser);
        if (setBasicInfo) {
            return ReturnMap.result(0, "保存成功!");
        } else {

            return ReturnMap.result(1, "保存失败！");
        }
    }

    @ApiOperation(value = "用户登陆状态", notes = "判断当前用户是否已登陆")
    @AllowAnonymous
    @GetMapping(value = "isLogin")
    @ResponseBody
    public Object isLogin() {
        LoginInfo loginInfo = LoginUserThreadLocal.getLoginInfo();

        if (loginInfo != null && loginInfo.getRegisterId() > 0) {

            return ReturnMap.result(0, "已登录");
        }
        return ReturnMap.result(1, "未登录");
    }

    @ApiOperation(value = "修改手机号", notes = "登录的状态下修改手机号码")
    @PostMapping(value = "updatePhone")
    @ResponseBody
    public Object updatePhone(@RequestBody RegisterUser registerUser) {

        //验证是否已登录
        LoginInfo loginInfo = LoginUserThreadLocal.getLoginInfo();
        if (loginInfo == null || loginInfo.getRegisterId() == null || loginInfo.getRegisterId() < 1) {
            return ReturnMap.result(1, "未登录，请先登录");
        }

        //验证参数
        if (registerUser != null) {
            String newPhoneNo = registerUser.getAccount();
            if (StringUtils.isEmpty(newPhoneNo)) {
                return ReturnMap.result(1, "新手机号不能为空");
            }
            if (StringUtils.isEmpty(registerUser.getCode())) {
                return ReturnMap.result(1, "验证码不能为空");
            }
            if (!RegexValidateUtil.checkMobileNumber(newPhoneNo)) {
                return ReturnMap.result(1, "输入的手机格式不正确");
            }
            if (loginInfo.getAccount().equals(newPhoneNo)) {
                return ReturnMap.result(1, "不可重复验证");
            }
        } else {
            return ReturnMap.result(1, "请求参数不能为空");
        }
        Boolean existByPhoneNo = authService.isExistByPhoneNo(registerUser.getAccount());
        if (existByPhoneNo) {
            return ReturnMap.result(1, "此手机号已存在，不能再次绑定");
        }
        Boolean check = check(registerUser.getAccount(), registerUser.getCode());
        if (!check) {
            return ReturnMap.result(1, "验证码错误！");
        }
        registerUser.setRegisterId(loginInfo.getRegisterId());
        Boolean setBasicInfo = authService.setBasicInfo(registerUser);
        if (setBasicInfo) {
            return ReturnMap.result(0, "新手机号修改成功！", registerUser.getAccount());
        }
        return ReturnMap.result(1, "新手机号修改失败！");

    }


    /**
     * 验证验证码
     *
     * @param phoneNo 手机号
     * @param code    验证码
     * @return boolean 验证码是否正确
     */
    public Boolean check(String phoneNo, String code) {
        String realCode = redisService.stringGetStringByKey("VerificationCode:" + SmsProjectName.BAG_APP.getDesc() + ":" + phoneNo);
        if (code.equals(realCode)) {
            return true;
        }
        return false;
    }

}
