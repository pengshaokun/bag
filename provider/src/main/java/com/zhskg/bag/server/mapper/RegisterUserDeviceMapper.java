package com.zhskg.bag.server.mapper;

import com.zhskg.bag.entity.RegisterUserDevice;

public interface RegisterUserDeviceMapper {
    int insert(RegisterUserDevice record);

    int insertSelective(RegisterUserDevice record);

    RegisterUserDevice selectByPrimaryKey(Long deviceId);

    int updateByPrimaryKeySelective(RegisterUserDevice record);

    int updateByPrimaryKey(RegisterUserDevice record);
}