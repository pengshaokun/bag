package com.zhskg.bag.server.mapper;

import com.zhskg.bag.entity.RegisterAuthentication;

public interface RegisterAuthenticationMapper {
    int insert(RegisterAuthentication record);

    int insertSelective(RegisterAuthentication record);

    RegisterAuthentication selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RegisterAuthentication record);

    int updateByPrimaryKey(RegisterAuthentication record);
}