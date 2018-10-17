package com.zhskg.bag.service;

import com.zhskg.bag.entity.BagInfo;
import com.zhskg.bag.param.AppBagInfoDto;
import com.zhskg.bag.param.BagInfoParam;

import java.util.List;

/**
 * @author jean
 * @date 2018/10/6
 * desc: 验证
 */
public interface IBagInfoService {


    /**
     * 保存丢失信息
     * @param entry
     * @return
     */
    int save(BagInfo entry);


    /**
     * 根据箱包ID获取箱包列表
     * @param param
     * @return
     */
    List<BagInfo> getList(BagInfoParam param);

    /**
     * 修改箱包状态
     * @param record
     * @return
     */
    int updateByPrimaryKey(BagInfo record);

    /**
     * @author qiuw
     * @description 添加箱包信息
     * @date 2018/10/15 16:19
     * @param
     * @return Long 返回自增id
     */
    Long  addBagInfo(AppBagInfoDto appBagInfoDto);

    /**
     * @author Qiuw
     * @description 根据箱包唯一编码获取箱包信息
     * @date 2018/10/15 17:26
     * @param bagId
     * @return com.zhskg.bag.entity.BagInfo
     */
    BagInfo getBagInfoByBagId(String bagId);

    /**
     * @author Qiuw
     * @description 用户修改箱包信息
     * @date 2018/10/15 20:28
     * @param
     * @return boolean
     */
    Boolean updateBagInfo(AppBagInfoDto appBagInfoDto);


}
