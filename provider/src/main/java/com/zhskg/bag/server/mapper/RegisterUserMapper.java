package com.zhskg.bag.server.mapper;

import com.zhskg.bag.entity.RegisterUser;

public interface RegisterUserMapper {
    int insert(RegisterUser record);

    int insertSelective(RegisterUser record);

    RegisterUser selectByPrimaryKey(Long registerId);

    int updateByPrimaryKeySelective(RegisterUser record);

    int updateByPrimaryKey(RegisterUser record);

    Integer checkEmail(String email);

    Integer checkPhoneNo(String phoneNo);


}