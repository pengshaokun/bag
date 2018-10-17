package com.zhskg.bag.server.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.zhskg.bag.entity.RegisterBagInfo;
import com.zhskg.bag.param.AppRegisterBagDto;
import com.zhskg.bag.server.mapper.RegisterBagInfoMapper;
import com.zhskg.bag.service.IRegisterBagInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author: Qiuw
 * @date: 2018/10/16 14:28
 * @description:
 */
@Service(version = "1.0")
@Slf4j
public class RegisterBagInfoServiceImpl implements IRegisterBagInfoService {

    @Autowired
    private RegisterBagInfoMapper registerBagInfoMapper;

    @Override
    public Boolean addRegisterBagInfo(AppRegisterBagDto appRegisterBagDto) {
        Long bagId = appRegisterBagDto.getBagInfoId();
        if (bagId == null || bagId <= 0) {
            log.error("箱包主键错误");
            return false;
        }
        Long userId = appRegisterBagDto.getRegisterId();
        if (userId == null || userId <= 0) {
            log.error("用户主键错误");
            return false;
        }
        RegisterBagInfo registerBagInfo = new RegisterBagInfo();
        BeanUtils.copyProperties(appRegisterBagDto,registerBagInfo);
        return registerBagInfoMapper.insertSelective(registerBagInfo) > 0;
    }

    @Override
    public List<RegisterBagInfo> getRegisterBagInfoByUserIdAndBagId(Long registerUserId, Long bagInfoId) {
        return registerBagInfoMapper.selectByRegisterUserIdAndBagId(registerUserId,bagInfoId);
    }
}
