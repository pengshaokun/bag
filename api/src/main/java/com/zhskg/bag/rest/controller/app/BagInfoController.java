package com.zhskg.bag.rest.controller.app;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.zhskg.bag.common.constans.Constans;
import com.zhskg.bag.common.util.ReturnMap;
import com.zhskg.bag.entity.BagInfo;
import com.zhskg.bag.entity.RegisterBagInfo;
import com.zhskg.bag.param.AppBagInfoDto;
import com.zhskg.bag.param.AppRegisterBagDto;
import com.zhskg.bag.param.BagInfoParam;
import com.zhskg.bag.param.LoginInfo;
import com.zhskg.bag.rest.exception.ZhskgException;
import com.zhskg.bag.rest.exception.ZhskgExceptionEnum;
import com.zhskg.bag.rest.intercepter.AllowAnonymous;
import com.zhskg.bag.service.IBagInfoService;
import com.zhskg.bag.service.IRegisterBagInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * @author pengshaokun
 * @date 2018/10/7
 * desc:箱包信息控制器
 */
@Api(value = "箱包信息接口")
@RestController
@RequestMapping(value = "app/bagInfo/")
@Slf4j
public class BagInfoController {

    @Reference(version = "1.0")
    private IBagInfoService  bagInfoService;

    @Reference(version = "1.0")
    private IRegisterBagInfoService registerBagInfoService;

    /**
     * 保存挂失信息
     * @param entry
     * @return
     */
    @ApiOperation(value = "保存挂失信息")
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    @AllowAnonymous
    public Object save(@RequestBody BagInfo entry) {

        //获取当前用户信息
        //LoginInfo loginInfo = LoginUserThreadLocal.getLoginInfo();
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setRegisterId(1L);
        if (loginInfo == null || loginInfo.getRegisterId() == null) {
            return ReturnMap.result(0, "未登录，请登录！！");
        }

        try {
            if (StringUtils.isEmpty(entry.getBagId())) {
                return ReturnMap.result(0, "设备编码不能为空！！");
            }

            BagInfoParam bagInfoParam = new BagInfoParam();
            bagInfoParam.setBagId(entry.getBagId());

            List<BagInfo> list = bagInfoService.getList(bagInfoParam);

            if (list.size() > 0 && list.get(0).getStatus() == 1) {
                return ReturnMap.result(0, "该设备已经挂失过，请勿重复挂失！");
            }
            int operateFlag=0;
            if (list.size()>0){
                //flag=1 时 说明有挂失记录但状态为未挂失状态
                operateFlag=1;
            }
            int num;
            if (operateFlag==1){
                Date date = new Date();
                BagInfo outLostInfo=list.get(0);
                outLostInfo.setStatus(1);
                outLostInfo.setLostTime(date);
                num= bagInfoService.updateByPrimaryKey(outLostInfo);
                if(num > 0){
                    return ReturnMap.result(1, "挂失成功");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnMap.result(0, "操作失败");
    }

    /**
     * 撤销挂失
     * @param entry 条件参数对象
     */
    @ApiOperation(value = "撤销挂失")
    @RequestMapping(value = "cancel", method = RequestMethod.POST)
    @ResponseBody
    @AllowAnonymous
    public Object setStatus(@RequestBody BagInfo entry) {

        //获取当前用户信息
        //LoginInfo loginInfo = LoginUserThreadLocal.getLoginInfo();
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setRegisterId(1L);
        if (loginInfo == null || loginInfo.getRegisterId() == null) {
            return ReturnMap.result(0, "未登录，请登录！！");
        }


        try {
            BagInfoParam bagInfoParam = new BagInfoParam();
            bagInfoParam.setBagId(entry.getBagId());

            List<BagInfo> list = bagInfoService.getList(bagInfoParam);
            if (list.size() > 0 && list.get(0).getStatus() == 0) {
                return ReturnMap.result(0, "该设备未挂失，无法撤销！");
            }

            int operateFlag=0;
            if (list.size()>0){
                //flag=1 时 说明有挂失记录但状态为未挂失状态
                operateFlag=1;
            }
            int num;
            if (operateFlag==1){
                Date date = new Date();
                BagInfo outLostInfo=list.get(0);
                outLostInfo.setStatus(0);
                num= bagInfoService.updateByPrimaryKey(outLostInfo);
                if(num > 0){
                    return ReturnMap.result(1, "撤销挂失成功");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ReturnMap.result(0, "撤销失败");
    }

    /**
     * 我申请的挂失
     */
    @ApiOperation(value = "我申请的挂失")
    @RequestMapping(value = "apply", method = RequestMethod.GET)
    @ResponseBody
    @AllowAnonymous
    public Object getLostInfo(BagInfoParam param){

        //获取当前用户信息
        //LoginInfo loginInfo = LoginUserThreadLocal.getLoginInfo();
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setRegisterId(1L);
        if (loginInfo == null || loginInfo.getRegisterId() == null) {
            return ReturnMap.result(0, "未登录，请登录！！");
        }

        try {
            if (StringUtils.isEmpty(param.getBagId())){
                return ReturnMap.result(0, "设备编码不能为空！！");
            }

            param.setBagId(param.getBagId());
            param.setStatus(1);
            List<BagInfo> list = bagInfoService.getList(param);

            JSONObject object = new JSONObject();
            if (list.size()>0) {
                BagInfo entry = list.get(0);
                object.put("bagId",entry.getBagId());
                object.put("id",entry.getBagInfoId());
                object.put("productName",entry.getProductName());
                object.put("lostTime",entry.getLostTime());
                object.put("status",1);
                return ReturnMap.result(1, "success",object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnMap.result(0, "查询挂失失败");

    }

    /**
     * @param appBagInfoDto, results
     * @return java.lang.Object
     * @author Qiuw
     * @description 箱包添加
     * @date 2018/10/15 17:11
     */
    @ApiOperation(value = "添加箱包", notes = "添加箱包", produces = "application/octet-stream")
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public Object addBagInfo(@RequestBody @Valid AppBagInfoDto appBagInfoDto, BindingResult results) {
        //获取当前用户信息
        //LoginInfo loginInfo = LoginUserThreadLocal.getLoginInfo();
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setRegisterId(1L);
        if (loginInfo == null || loginInfo.getRegisterId() == null) {
            throw new ZhskgException(ZhskgExceptionEnum.AUTHEN_ERROR);
        }
        // TODO 在箱包仓库中检验传入的箱包唯一id是否存在和正确
        //参数校验
        if (results.hasErrors()) {
            throw new ZhskgException(ZhskgExceptionEnum.PARAM_VALID_ERROR, results.getFieldError().getDefaultMessage());
        }
        //验证数据库箱包是否被绑定
        BagInfo bagInfoData = bagInfoService.getBagInfoByBagId(appBagInfoDto.getBagId());
        if (bagInfoData != null) {
            throw new ZhskgException(ZhskgExceptionEnum.BAG_EXIST);
        }
        Long bagInfoId = bagInfoService.addBagInfo(appBagInfoDto);
        if (bagInfoId > 0) {
            //往注册用户箱包表插入数据
            Long userId = loginInfo.getRegisterId();
            List<RegisterBagInfo> reBagList = registerBagInfoService.getRegisterBagInfoByUserIdAndBagId(userId, bagInfoId);
            if (reBagList.size() > 0) {
                log.info("注册用户箱包已存在");
            } else {
                AppRegisterBagDto registerBagInfo = new AppRegisterBagDto();
                registerBagInfo.setBagInfoId(bagInfoId);
                registerBagInfo.setRegisterId(userId);
                registerBagInfo.setStatus(0);
                registerBagInfo.setAuthId(userId);
                registerBagInfo.setLight(Constans.BAGLIGHT);
                registerBagInfo.setAuthTime(new Date());
                Boolean regBagFlag = registerBagInfoService.addRegisterBagInfo(registerBagInfo);
                if (regBagFlag) {
                    log.info("注册用户箱包新增成功");
                } else {
                    log.error("注册用户箱包新增失败");
                }
            }
            return ReturnMap.result(0, "添加成功");
        }
        return ReturnMap.result(1, "添加失败");
    }

    /**
     * @param appBagInfoDto
     * @return java.lang.Object
     * @author Qiuw
     * @description 更新箱包
     * @date 2018/10/16 11:03
     */
    @ApiOperation(value = "更新箱包", notes = "更新箱包", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public Object updateBagInfo(@RequestBody AppBagInfoDto appBagInfoDto) {
        Boolean flag = bagInfoService.updateBagInfo(appBagInfoDto);
        if (flag) {
            return ReturnMap.result(0, "更新成功");
        }
        return ReturnMap.result(1, "更新失败");
    }



}
