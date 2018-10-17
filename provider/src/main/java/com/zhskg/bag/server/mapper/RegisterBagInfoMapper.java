package com.zhskg.bag.server.mapper;

import com.zhskg.bag.entity.RegisterBagInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RegisterBagInfoMapper {
    int insert(RegisterBagInfo record);

    int insertSelective(RegisterBagInfo record);

    RegisterBagInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RegisterBagInfo record);

    int updateByPrimaryKey(RegisterBagInfo record);

    /**
    *通过用户id和箱包id查询记录是否存在
    */
    List<RegisterBagInfo> selectByRegisterUserIdAndBagId(@Param("registerUserId") Long registerUserId, @Param("bagId") Long bagId);

}