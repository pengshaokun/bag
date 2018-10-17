package com.zhskg.bag.server.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.zhskg.bag.entity.BagLocation;
import com.zhskg.bag.server.mapper.BagLocationMapper;
import com.zhskg.bag.service.IBagLocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author pengshaokun
 * @date 2018/10/6
 * desc:
 */
@Service(version = "1.0")
@Slf4j
public class BagLocationServiceImpl implements IBagLocationService {


    @Autowired
    private BagLocationMapper bagLocationMapper;

    @Override
    public BagLocation getLst(BagLocation bagLocation) {
        return bagLocationMapper.selectByPrimaryKey(bagLocation.getBagInfoId());
    }
}
