package com.example.api.common.bean;

import com.example.api.config.redis.RedisFactory;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

@Component
public class RedisUtils {

  private static RedisFactory redisFactory;

  @Autowired(required = false)
  private RedisUtils(RedisFactory redisFactory) {
    RedisUtils.redisFactory = redisFactory;
  }

  public static boolean set(String cacheKey, String value){
    return "OK".contains(redisFactory.set(cacheKey, value));
  }

  public static String get(String cacheKey) {
    return redisFactory.get(cacheKey);
  }

  public static Set<String> keys(String pattern) {
    return redisFactory.keys(pattern);
  }

  public static Long del(String cacheKey) {
    return redisFactory.del(cacheKey);
  }

  /**
   * 获取key 过期时间
   * @param cacheKey
   * @return 单位 ： 秒
   */
  public static Long getExpire(String cacheKey) {
    return redisFactory.ttl(cacheKey);
  }

  public static boolean setExpire(String cacheKey, int seconds) {
    return redisFactory.setExpire(cacheKey, seconds) > 0;
  }

  public static Long increment(String cacheKey, long number) {
    return redisFactory.incr(cacheKey, number);
  }

  /**
   * 判断 key 是否存在
   * @param cacheKey
   * @return
   */
  public static boolean hasKey(String cacheKey) {
    return redisFactory.exists(cacheKey);
  }

  /**
   *
   * @param cacheKey key
   * @param expireSec 过期时间 单位秒
   * @param data 数据
   */
  public static boolean setex(String cacheKey, int expireSec, String data) {

    String ret = redisFactory.setex(cacheKey,expireSec,data);

    if (ret != null && ret.contains("OK")) {
      return true;
    }

    return false;
  }

  /**
   * zset 集合中的元素个数
   * @param key
   * @return
   */
  public static Long zcard(String key) {
    return redisFactory.zcard(key);
  }

  /**
   * redis zset 结构
   * 如果该value已经存在则根据score更新元素
   * @param key
   * @param data
   * @param score score就是用来排序的
   * @return
   */
  public static boolean zadd(String key, String data, double score) {
    return redisFactory.zadd(key, score, data) > 0;
  }

  /**
   * 返回集合内元素在指定分数范围内的排名（从小到大）
   * @param key
   * @param start
   * @param end
   * @return
   */
  public static Set<String> zrevrange(String key, int start, int end) {
    return redisFactory.zrevrange(key, start, end);
  }

  /**
   * 删除指定分数范围内的元素
   * @param key
   * @param start
   * @param end
   * @return
   */
  public static Long removeRangeByScore(String key, long start, long end) {
    return redisFactory.removeRangeByScore(key, start, end);
  }

  public static String hget(String key, String field) {
    Jedis jedis = redisFactory.getResource();
    String ret;
    try {
      ret = jedis.hget(key, field);
    } finally {
      jedis.close();
    }

    return ret;
  }

  public static boolean hmset(String key, Map<String, String> item) {
    return redisFactory.hmset(key, item).contains("OK");
  }

  public static long hsize(String key) {
    Jedis jedis = redisFactory.getResource();
    Long ret;
    try {
      ret = jedis.hlen(key);
    } finally {
      jedis.close();
    }
    return 0;
  }

  public static long hincrby(String key, String field, long value) {
    Jedis jedis = redisFactory.getResource();
    Long ret;
    try {
      ret = jedis.hincrBy(key, field, value);
    } finally {
      jedis.close();
    }
    return 0;
  }

  public static List<Object> executePipelinedHash(Set<String> keys) {
    Jedis jedis = redisFactory.getResource();
    try {
      Pipeline p = jedis.pipelined();
      try {
        for (String key : keys) {
          p.hgetAll(key);
        }
        return p.syncAndReturnAll();
      } finally {
        p.close();
      }
    } catch (Exception e){

    } finally {
      jedis.close();
    }
    return null;
  }

  public static boolean lock(String key, long timeout, long lockExpireTime) {
    return redisFactory.lock(key, timeout, lockExpireTime);
  }

  public static boolean unlock(String key) {
    return redisFactory.unLock(key) > 0;
  }
}
