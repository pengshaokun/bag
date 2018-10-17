package com.zhskg.bag.server.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.zhskg.bag.server.service.IRedisTemplateService;
import com.zhskg.bag.service.IRedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangshengyue
 * @date 2018/10/7
 * desc:
 */
@Service(version = "1.0")
@Slf4j
public class RedisServiceImpl implements IRedisService {

    @Autowired
    private IRedisTemplateService redisTemplateService;

    @Override
    public void deleteFromRedis(String key) {
        redisTemplateService.deleteFromRedis(key);
    }

    @Override
    public Boolean hashCheckHxists(String mapName, String field) {
        return redisTemplateService.hashCheckHxists(mapName, field);
    }

    @Override
    public Object hashGet(String tableName, String hashKey) {
        return redisTemplateService.hashGet(tableName, hashKey);
    }

    @Override
    public Map<Object, Object> hashGetAll(String key) {
        return redisTemplateService.hashGetAll(key);
    }

    @Override
    public Long hashIncrementLongOfHashMap(String hKey, String hashKey, Long delta) {
        return redisTemplateService.hashIncrementLongOfHashMap(hKey, hashKey, delta);
    }

    @Override
    public Double hashIncrementDoubleOfHashMap(String hKey, String hashKey, Double delta) {
        return redisTemplateService.hashIncrementDoubleOfHashMap(hKey, hashKey, delta);
    }

    @Override
    public void hashPushHashMap(String key, Object hashKey, Object value) {
        redisTemplateService.hashPushHashMap(key, hashKey, value);
    }

    @Override
    public Set<Object> hashGetAllHashKey(String key) {
        return redisTemplateService.hashGetAllHashKey(key);
    }

    @Override
    public Long hashGetHashMapSize(String key) {
        return redisTemplateService.hashGetHashMapSize(key);
    }

    @Override
    public List<Object> hashGetHashAllValues(String key) {
        return redisTemplateService.hashGetHashAllValues(key);
    }

    @Override
    public Long hashDeleteHashKey(String key, Object... hashKeys) {
        return redisTemplateService.hashDeleteHashKey(key, hashKeys);
    }

    @Override
    public void listLeftPushList(String key, String value) {
        redisTemplateService.listLeftPushList(key, value);
    }

    @Override
    public String listLeftPopList(String key) {
        return redisTemplateService.listLeftPopList(key);
    }

    @Override
    public Long listSize(String key) {
        return redisTemplateService.listSize(key);
    }

    @Override
    public List<String> listRangeList(String key, Long start, Long end) {
        return redisTemplateService.listRangeList(key, start, end);
    }

    @Override
    public Long listRemoveFromList(String key, long i, Object value) {
        return redisTemplateService.listRemoveFromList(key, i, value);
    }

    @Override
    public String listIndexFromList(String key, long index) {
        return redisTemplateService.listIndexFromList(key, index);
    }

    @Override
    public void listSetValueToList(String key, long index, String value) {
        redisTemplateService.listSetValueToList(key, index, value);
    }

    @Override
    public void listTrimByRange(String key, Long start, Long end) {
        redisTemplateService.listTrimByRange(key, start, end);
    }

    @Override
    public void listRightPushList(String key, String value) {
        redisTemplateService.listRightPushList(key, value);
    }

    @Override
    public String listRightPopList(String key) {
        return redisTemplateService.listRightPopList(key);
    }

    @Override
    public Long setAddSetMap(String key, String... values) {
        return redisTemplateService.setAddSetMap(key, values);
    }

    @Override
    public Long setGetSizeForSetMap(String key) {
        return redisTemplateService.setGetSizeForSetMap(key);
    }

    @Override
    public Set<String> setGetMemberOfSetMap(String key) {
        return redisTemplateService.setGetMemberOfSetMap(key);
    }

    @Override
    public Boolean setCheckIsMemberOfSet(String key, Object o) {
        return redisTemplateService.setCheckIsMemberOfSet(key, o);
    }

    @Override
    public Integer stringAppendString(String key, String value) {
        return redisTemplateService.stringAppendString(key, value);
    }

    @Override
    public String stringGetStringByKey(String key) {
        return redisTemplateService.stringGetStringByKey(key);
    }

    @Override
    public String stringGetSubStringFromString(String key, long start, long end) {
        return redisTemplateService.stringGetSubStringFromString(key, start, end);
    }

    @Override
    public Long stringIncrementLongString(String key, Long delta) {
        return redisTemplateService.stringIncrementLongString(key, delta);
    }

    @Override
    public Double stringIncrementDoubleString(String key, Double delta) {
        return redisTemplateService.stringIncrementDoubleString(key, delta);
    }

    @Override
    public void stringSetString(String key, String value) {
        redisTemplateService.stringSetString(key, value);
    }

    @Override
    public String stringGetAndSet(String key, String value) {
        return redisTemplateService.stringGetAndSet(key, value);
    }

    @Override
    public void setStringAndExpireTime(String key, String value, long timeout,TimeUnit timeUnit) {
        redisTemplateService.stringSetValueAndExpireTime(key, value, timeout,timeUnit );
    }

    @Override
    public Long getExpire(String key) {
        return redisTemplateService.getExpire(key);
    }

}
