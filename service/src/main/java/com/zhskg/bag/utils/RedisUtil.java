//package com.zhskg.bag.utils;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import javax.validation.constraints.NotNull;
//
//import com.zhskg.bag.util.EncryptUtil;
//import redis.clients.jedis.*;
//
//
//public class RedisUtil
//{
//    //访问密码
//    private static String AUTH = "";
//
//    //可用连接实例的最大数目，默认值为8；
//    //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
//    private static int MAX_ACTIVE = 1024;
//
//    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
//    private static int MAX_IDLE = 20;
//
//    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
//    private static int MAX_WAIT = 10000;
//
//    private static int TIMEOUT = 10000;
//
//    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
//    private static boolean TEST_ON_BORROW = true;
//
//    private static JedisPool jedisPool = null;
//
//    private static JedisCluster jedisCluster = null;
//
//    /**
//     * 初始化Redis连接池
//     */
//    static
//    {
//        try {
//            JedisPoolConfig config = new JedisPoolConfig();
//            config.setMaxTotal(MAX_ACTIVE);
//            config.setMaxIdle(MAX_IDLE);
//            config.setMaxWaitMillis(MAX_WAIT);
//            config.setTestOnBorrow(TEST_ON_BORROW);
//            if (ConfigUtil.Redis_Cluster){
//                Set<HostAndPort> jedisClusterNodes = new HashSet<>();
//                String[] ADDRESS = ConfigUtil.Redis_URL.split(";");
//                for (String url : ADDRESS) {
//                    jedisClusterNodes.add(new HostAndPort(url.split(":")[0], Integer.parseInt(url.split(":")[1])));
//                }
//                // 根据节点集创集群链接对象
//                //JedisCluster jedisCluster = newJedisCluster(jedisClusterNodes);
//                // 节点，超时时间，最多重定向次数，链接池
//                jedisCluster = new JedisCluster(jedisClusterNodes, 2000, 10, config);
//            }else {
//
//                jedisPool = new JedisPool(config, ConfigUtil.Redis_URL, ConfigUtil.Redis_port, TIMEOUT, EncryptUtil.getFromBase64(ConfigUtil.Redis_PWD));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 释放资源
//     * @param jedis
//     * @param
//     */
//    public static void returnResource(JedisCommands jedis) {
//        if (jedis != null) {
//            if (jedis instanceof Jedis){
//                ((Jedis) jedis).close();
//            }else if (jedis instanceof JedisCluster){
//                try {
//                    ((JedisCluster) jedis).close();
//                }catch (Exception e){
//
//                }
//            }else if (jedis instanceof ShardedJedis){
//                ((ShardedJedis) jedis).close();
//            }
//        }
//    }
//
//
//    /**
//     * 获取Jedis实例
//     * @return
//     */
//    public static JedisCommands getJedis() {
//        try {
//            if(ConfigUtil.Redis_Cluster)
//            {
//                if (jedisCluster != null) {
//                    return jedisCluster;
//                } else {
//                    return null;
//                }
//            }
//            else {
//                if (jedisPool != null) {
//                    Jedis resource = jedisPool.getResource();
//                    return resource;
//                } else {
//                    return null;
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public static String set(String key,String val)
//    {
//        String result = null;
//        JedisCommands jedis = null;
//        try {
//            jedis = getJedis();
//            result = jedis.set(key, val);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            returnResource(jedis);
//        }
//        return result;
//    }
//    public static String setex(String key, int seconds, String value)
//    {
//        String result = null;
//        JedisCommands jedis = null;
//        try {
//            jedis = getJedis();
//            result = jedis.setex(key, seconds, value);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            returnResource(jedis);
//        }
//        return result;
//    }
//
//    public static String get(String key)
//    {
//        String result = null;
//        JedisCommands jedis = null;
//        try {
//            jedis = getJedis();
//            result = jedis.get(key);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            returnResource(jedis);
//        }
//        return result;
//    }
//
//    public static Boolean exists(String key)
//    {
//        //Jedis jedis = jedisPool.getResource();
//        JedisCommands jedis = getJedis();
//        try {
//            return jedis.exists(key);
//        }
//        catch (Exception ex){
//            return false;
//        }finally {
//            returnResource(jedis);
//        }
//    }
//
//    public static boolean delete(String key)
//    {
//        //Jedis jedis = jedisPool.getResource();
//        JedisCommands jedis = getJedis();
//        try {
//            long rs=jedis.del(key);
//            if (rs>0) {
//                return true;
//            }
//            return false;
//        }
//        catch (Exception ex){
//            return false;
//        }finally {
//            returnResource(jedis);
//        }
//    }
//    /**
//     * 修改过期时间
//     * @param key
//     * @param time
//     * @return
//     */
//    public static boolean expire(@NotNull String key,int time){
//        JedisCommands jedis = null;
//        try {
//            jedis = getJedis();
//            if(jedis.exists(key)){
//                jedis.expire(key, time);
//                return true;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            returnResource(jedis);
//        }
//        return false;
//    }
//
//    public static long ddlKey(String key){
//        JedisCommands jedis = getJedis();
//        try {
//            long rs=jedis.ttl(key);
//            return rs;
//        }
//        catch (Exception ex){
//            return 0L;
//        }finally {
//            returnResource(jedis);
//        }
//    }
//}
