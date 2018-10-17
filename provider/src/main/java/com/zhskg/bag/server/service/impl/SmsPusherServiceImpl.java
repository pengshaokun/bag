package com.zhskg.bag.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import com.zhskg.bag.server.config.properties.SmsProperties;
import com.zhskg.bag.server.service.ISmsPushService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * @author zhangshengyue
 * @date 2018/10/7
 * desc: 短信服务
 */
@Slf4j
@Service
public class SmsPusherServiceImpl implements ISmsPushService {

    @Autowired
    private SmsProperties smsProperties;


    @Resource(name = "threadPool")
    private ThreadPoolExecutor threadPoolExecutor;


    @Override
    public boolean sendSms(Map<String, Object> param, String phoneNo) {
        String params = JSON.toJSONString(param);
        CompletableFuture<Boolean> booleanCompletableFuture = CompletableFuture.supplyAsync(() -> doSendMsg(params, phoneNo), threadPoolExecutor);
        try {
            return booleanCompletableFuture.get(2, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("短信发送异常 param:{} phoneNo:{}", param, phoneNo, e);
        }
        return false;
    }

    private Boolean doSendMsg(String params, String mobile) {
        Boolean result = Boolean.FALSE;
        TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", smsProperties.getAppKey(), smsProperties.getAppSecret());
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setSmsFreeSignName(smsProperties.getRegister().getSign());
        req.setSmsParamString(params);
        req.setRecNum(mobile);
        req.setSmsType("normal");
        req.setSmsTemplateCode(smsProperties.getRegister().getTemplate());
        try {
            AlibabaAliqinFcSmsNumSendResponse execute = client.execute(req);
            if (Objects.nonNull(execute) && Objects.nonNull(execute.getResult())) {
                result = execute.getResult().getSuccess();
            }
        } catch (Exception ex) {
            log.error("短信发送失败 param:{} mobile:{}", params, mobile, ex);
        }
        return result;

    }
}
