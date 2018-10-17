package com.zhskg.bag.server.mapper;

import com.zhskg.bag.entity.LogSysUserLogin;

public interface LogSysUserLoginMapper {
    int insert(LogSysUserLogin record);

    int insertSelective(LogSysUserLogin record);

    LogSysUserLogin selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LogSysUserLogin record);

    int updateByPrimaryKey(LogSysUserLogin record);
}