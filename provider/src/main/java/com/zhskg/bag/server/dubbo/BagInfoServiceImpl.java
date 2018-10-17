package com.zhskg.bag.server.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.zhskg.bag.common.constans.Constans;
import com.zhskg.bag.entity.BagInfo;
import com.zhskg.bag.param.*;
import com.zhskg.bag.server.mapper.BagInfoMapper;
import com.zhskg.bag.service.IBagInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author pengshaokun
 * @date 2018/10/6
 * desc:
 */
@Service(version = "1.0")
@Slf4j
public class BagInfoServiceImpl implements IBagInfoService {


    @Autowired
    private BagInfoMapper bagInfoMapper;


    @Override
    public int save(BagInfo entry) {

        return bagInfoMapper.insert(entry);
    }

    @Override
    public List<BagInfo> getList(BagInfoParam param) {
        Map<String, Object> map = JSON.parseObject(JSON.toJSONString(param));
        return bagInfoMapper.getList(map);
    }

    @Override
    public int updateByPrimaryKey(BagInfo record) {
        return bagInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public Long addBagInfo(AppBagInfoDto appBagInfoDto) {
        BagInfo bagInfo = new BagInfo();
        bagInfo.setAddTime(new Date());
        // TODO 箱包信息id从数据库中读取
        // bagInfo.setBagId(appBagInfoDto.getBagId());
        // bagInfo.setBleMac(appBagInfoDto.getBleMac());
        BeanUtils.copyProperties(appBagInfoDto, bagInfo);
        // TODO 箱包默认连接密码从数据库中读取
        bagInfo.setConnPassword("pwd");
        // TODO 箱包出厂编号从数据库中读取
        bagInfo.setFactoryNo("fa0001");
        bagInfo.setProductName(Constans.BAGNAME);
        bagInfo.setStatus(0);
        bagInfoMapper.insertSelective(bagInfo);
        return bagInfo.getBagInfoId();
    }

    @Override
    public BagInfo getBagInfoByBagId(String bagId) {
        return bagInfoMapper.selectByBagId(bagId);
    }

    @Override
    public Boolean updateBagInfo(AppBagInfoDto appBagInfoDto) {
        Long primaryKey = appBagInfoDto.getBagInfoId();
        if (primaryKey == null || primaryKey <= 0) {
            log.error("箱包主键错误");
            return false;
        }
        BagInfo bagInfo = new BagInfo();
        BeanUtils.copyProperties(appBagInfoDto, bagInfo);
        //箱包的出厂编号和唯一编号不能修改
        bagInfo.setFactoryNo(null);
        bagInfo.setBagId(null);
        return bagInfoMapper.updateByPrimaryKeySelective(bagInfo) > 0;
    }

}
