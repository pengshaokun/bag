package com.zhskg.bag.server.mapper;

import com.zhskg.bag.entity.BagInfo;
import com.zhskg.bag.param.BagInfoParam;

import java.util.List;
import java.util.Map;

public interface BagInfoMapper {
    int insert(BagInfo record);

    int insertSelective(BagInfo record);

    BagInfo selectByPrimaryKey(Long bagInfoId);

    int updateByPrimaryKeySelective(BagInfo record);

    int updateByPrimaryKey(BagInfo record);


    /**
    * @author Qiuw
    * @description 根据箱包唯一编号查询箱包信息
    * @date 2018/10/15 17:24
    * @param bagId
    * @return com.zhskg.bag.entity.BagInfo
    */
    BagInfo selectByBagId(String bagId);

    List<BagInfo> getList(Map<String, Object> map);

}