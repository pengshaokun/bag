package com.zhskg.bag.server.mapper;

import com.zhskg.bag.entity.BagLocation;

public interface BagLocationMapper {
    int insert(BagLocation record);

    int insertSelective(BagLocation record);

    BagLocation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BagLocation record);

    int updateByPrimaryKey(BagLocation record);
}