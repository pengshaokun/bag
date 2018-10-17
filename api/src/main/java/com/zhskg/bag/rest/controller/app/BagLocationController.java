package com.zhskg.bag.rest.controller.app;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zhskg.bag.common.util.ReturnMap;
import com.zhskg.bag.entity.BagLocation;
import com.zhskg.bag.rest.intercepter.AllowAnonymous;
import com.zhskg.bag.service.IBagLocationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author pengshaokun
 * @date 2018/10/7
 * desc:箱包位置控制器
 */
@Api(value = "箱包位置接口")
@RestController
@RequestMapping(value = "app/lost/")
public class BagLocationController {

    @Reference(version = "1.0", timeout = 600000)
    private IBagLocationService  bagLocationService;



    /**
     *
     * @param entry 条件参数对象
     */
    @ApiOperation(value = "获取箱包位置")
    @RequestMapping(value = "getLst", method = RequestMethod.POST)
    @ResponseBody
    @AllowAnonymous
    public Object getLst(@RequestBody BagLocation entry) {
        if (null == entry.getBagInfoId()) {
            return ReturnMap.result(0, "设备编码不能为空！！");
        }
        try {
            BagLocation lst = bagLocationService.getLst(entry);
            if(null != lst){
                return ReturnMap.result(1, "success",lst);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnMap.result(0, "获取箱包位置失败");
    }


    }
