package com.zhskg.bag.server.mapper;

import com.zhskg.bag.entity.LogRegisterLogin;

public interface LogRegisterLoginMapper {
    int insert(LogRegisterLogin record);

    int insertSelective(LogRegisterLogin record);

    LogRegisterLogin selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LogRegisterLogin record);

    int updateByPrimaryKey(LogRegisterLogin record);
}