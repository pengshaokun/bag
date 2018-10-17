package com.zhskg.bag.rest.intercepter;

import com.zhskg.bag.common.util.ReturnMap;
import com.zhskg.bag.rest.exception.ZhskgException;
import com.zhskg.bag.rest.exception.ZhskgExceptionEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;



/**
 * @Auther: qiuw
 * @Date: 2018/10/11 10:59
 * @Description: 全局异常拦截器（拦截所有的控制器）（带有@RequestMapping注解的方法上都会拦截）
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    /**
     * @Author qiuw
     * @Description: 拦截自定义异常
     * @Param: [e]
     * @Return: java.lang.Object
     * @Date: 2018/10/11 0011 14:22
     */
    @ExceptionHandler(value = ZhskgException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Object allExceptionHandler(ZhskgException e){
        log.error("自定义异常:{}",e.getMessage());
        return ReturnMap.result(e.getCode(),e.getMessage());
    }

   /**
    * @Author qiuw
    * @Description: 拦截未知的运行时异常
    * @Param: [e]
    * @Return: java.lang.Object
    * @Date: 2018/10/11  14:23
    */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Object notFount(RuntimeException e) {
        log.error("运行时异常:{}",e.getMessage());
        e.printStackTrace();
        return ReturnMap.result(ZhskgExceptionEnum.SERVER_ERROR.getCode(), ZhskgExceptionEnum.SERVER_ERROR.getMessage());
    }
}
