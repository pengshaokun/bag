package com.zhskg.bag.server.mapper;

import com.zhskg.bag.entity.ReportBag;

public interface ReportBagMapper {
    int insert(ReportBag record);

    int insertSelective(ReportBag record);

    ReportBag selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ReportBag record);

    int updateByPrimaryKey(ReportBag record);
}