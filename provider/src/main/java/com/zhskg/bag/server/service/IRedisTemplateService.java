package com.zhskg.bag.server.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author jean
 * @date 2018/10/7
 * desc: redis 服务
 */
public interface IRedisTemplateService {

    /**
     * @param key
     */
    void deleteFromRedis(String key);

    /**
     * @param hKey 哈希表名称
     * @param hashKey 域的hashKey
     * @return  如果哈希表含有给定域，返回 1 。如果哈希表不含有给定域，或 hashKey 不存在，返回 0 。
     */
    Boolean hashCheckHxists(String hKey, String hashKey);

    /**
     * @param hKey
     * @param hashKey
     * @return 给定域的值。当给定域不存在或是给定 key 不存在时，返回 nil 。
     */
    Object hashGet(String hKey, String hashKey);

    /**
     * @param key
     * @return
     */
    Map<Object, Object> hashGetAll(String key);

    /**
     *
     *增量也可以为负数，相当于对给定域进行减法操作。  如果 key 不存在，一个新的哈希表被创建并执行 HINCRBY 命令。
     *如果域 field 不存在，那么在执行命令前，域的值被初始化为 0 。  对一个储存字符串值的域 field 执行 HINCRBY 命令将造成一个错误。
     * @param hKey
     * @param hashKey
     * @param delta
     * @return 执行 HINCRBY 命令之后，哈希表 hKey 中域 hashKey 的值。
     */
    Long hashIncrementLongOfHashMap(String hKey, String hashKey, Long delta);

    /**
     * @param hKey
     * @param hashKey
     * @param delta
     * @return 执行 HINCRBY 命令之后，哈希表 hKey 中域 hashKey 的值。
     */
    Double hashIncrementDoubleOfHashMap(String hKey, String hashKey, Double delta);

    /**
     * @param key
     * @param hashKey
     * @param value
     */
    void hashPushHashMap(String key, Object hashKey, Object value);

    /**
     * @param key
     * @return
     */
    Set<Object> hashGetAllHashKey(String key);

    /**
     * @param key
     * @return
     */
    Long hashGetHashMapSize(String key);

    /**
     * @param key
     * @return
     */
    List<Object> hashGetHashAllValues(String key);

    /**
     * @param key
     * @param hashKeys
     * @return 返回值为被成功删除的数量
     */
    Long hashDeleteHashKey(String key, Object... hashKeys);

    //================================================List================================================

    /**
     * @param key
     * @param value
     */
    void listRightPushList(String key, String value);

    /**
     * @param key
     * @return
     */
    String listRightPopList(String key);

    /**
     * @param key
     * @param value
     */
    void listLeftPushList(String key, String value);

    /**
     * @param key
     * @return
     */
    String listLeftPopList(String key);

    /**
     * @param key
     * @return
     */
    Long listSize(String key);

    /**
     * @param key
     * @param start
     * @param end
     * @return
     */
    List<String> listRangeList(String key, Long start, Long end);

    /**
     * @param key
     * @param i
     * @param value
     * @return
     */
    Long listRemoveFromList(String key, long i, Object value);

    /**
     * @param key
     * @param index
     * @return
     */
    String listIndexFromList(String key, long index);

    /**
     * @param key
     * @param index
     * @param value
     */
    void listSetValueToList(String key, long index, String value);

    /**
     * @param key
     * @param start
     * @param end
     */
    void listTrimByRange(String key, Long start, Long end);

    //================================================Set================================================

    /**
     * @param key
     * @param values
     */
    Long setAddSetMap(String key, String...values);

    /**
     * @param key
     * @return
     */
    Long setGetSizeForSetMap(String key);

    /**
     * @param key
     * @return
     */
    Set<String> setGetMemberOfSetMap(String key);

    /**
     * @param key
     * @param o
     * @return
     */
    Boolean setCheckIsMemberOfSet(String key, Object o);

    //================================================String================================================

    /**
     *     如果 key 不存在， APPEND 就简单地将给定 key 设为 value ，就像执行 SET key value 一样。
     * @param key
     * @param value
     * @return 追加 value 之后， key 中字符串的长度
     */
    Integer stringAppendString(String key, String value);

    /**
     * @param key
     * @return
     */
    String stringGetStringByKey(String key);

    /**
     * @param key
     * @param start
     * @param end
     * @return 截取后的子字符串
     */
    String stringGetSubStringFromString(String key, long start, long end);

    /**
     * @param key
     * @param delta
     * @return 返回增长后的结果值
     */
    Long stringIncrementLongString(String key, Long delta);

    /**
     * @param key
     * @param delta
     * @return 返回增长后的结果值
     */
    Double stringIncrementDoubleString(String key, Double delta);

    /**
     * @param key
     * @param value
     */
    void stringSetString(String key, String value);

    /**
     * @param key
     * @param value
     * @return
     */
    String stringGetAndSet(String key, String value);

    /**
     * @param key
     * @param value
     * @param timeout
     * @return
     */
//    void setStringAndExpireTime(String key, String value, long timeout, TimeUnit timeUnit);
    void stringSetValueAndExpireTime(String key, String value, long timeout, TimeUnit timeUnit);

    /**
     * map
     * @param key
     * @param map
     * @param timeout
     * @param unit
     * @return
     */
    Boolean setMapAndExpireTime(String key,Map<String,Object> map,long timeout, TimeUnit unit);


    /**
     * map
     * @param key
     * @return
     */
    Long getExpire(String key);

//    void setObject(String key,Object object);



}
