package com.zhskg.bag.service;

import com.zhskg.bag.entity.RegisterBagInfo;
import com.zhskg.bag.param.AppRegisterBagDto;

import java.util.List;

/**
 * @author: Qiuw
 * @date: 2018/10/16 14:24
 * @description:
 */
public interface IRegisterBagInfoService {

    /**
     * @param appRegisterBagDto
     * @return java.lang.Boolean
     * @author Qiuw
     * @description 添加注册用户箱包
     * @date 2018/10/16 14:29
     */
    Boolean addRegisterBagInfo(AppRegisterBagDto appRegisterBagDto);

    /**
    * @author Qiuw
    * @description
    * @date 2018/10/16 17:05
    * @param registerUserId, bagInfoId
    * @return com.zhskg.bag.entity.RegisterBagInfo
    */
    List<RegisterBagInfo> getRegisterBagInfoByUserIdAndBagId(Long registerUserId, Long bagInfoId);
}
