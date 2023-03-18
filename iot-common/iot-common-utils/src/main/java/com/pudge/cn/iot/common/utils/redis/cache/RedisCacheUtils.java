package com.pudge.cn.iot.common.utils.redis.cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author mu_zhen
 * @description redis缓存工具类
 * @Date 2023/2/23 17:54
 */
public class RedisCacheUtils {
    @Autowired
    private static RedisTemplate<String, Object> redisTemplate;

    /**
     *  添加缓存：String  ，超时单位：s
     * @param key key
     * @param value value
     * @param time 超时时间s
     */
    
    static public void set(String key, Object value, long time) {
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
    }


    static public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }


    static public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }


    static  public Boolean del(String key) {
        return redisTemplate.delete(key);
    }


    static public Long del(List<String> keys) {
        return redisTemplate.delete(keys);
    }


    static public Boolean expire(String key, long time) {
        return redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }


    static public Long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }


    static  public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }


    static  public Long incr(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }


    static public Long decr(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, -delta);
    }


    static public Object hGet(String key, String hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }


    static public Boolean hSet(String key, String hashKey, Object value, long time) {
        redisTemplate.opsForHash().put(key, hashKey, value);
        return expire(key, time);
    }


    static public void hSet(String key, String hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }


    static public Map<Object, Object> hGetAll(String key) {
        return redisTemplate.opsForHash().entries(key);
    }


    static public Boolean hSetAll(String key, Map<String, Object> map, long time) {
        redisTemplate.opsForHash().putAll(key, map);
        return expire(key, time);
    }


    static public void hSetAll(String key, Map<String, ?> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }


    static public void hDel(String key, Object... hashKey) {
        redisTemplate.opsForHash().delete(key, hashKey);
    }


    static public Boolean hHasKey(String key, String hashKey) {
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }


    static public Long hIncr(String key, String hashKey, Long delta) {
        return redisTemplate.opsForHash().increment(key, hashKey, delta);
    }


    static public Long hDecr(String key, String hashKey, Long delta) {
        return redisTemplate.opsForHash().increment(key, hashKey, -delta);
    }


    static public Set<Object> sMembers(String key) {
        return redisTemplate.opsForSet().members(key);
    }


    static public Long sAdd(String key, Object... values) {
        return redisTemplate.opsForSet().add(key, values);
    }


    static public Long sAdd(String key, long time, Object... values) {
        Long count = redisTemplate.opsForSet().add(key, values);
        expire(key, time);
        return count;
    }


    static public Boolean sIsMember(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }


    static public Long sSize(String key) {
        return redisTemplate.opsForSet().size(key);
    }


    static  public Long sRemove(String key, Object... values) {
        return redisTemplate.opsForSet().remove(key, values);
    }


    static  public List<Object> lRange(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }


    static public Long lSize(String key) {
        return redisTemplate.opsForList().size(key);
    }


    static   public Object lIndex(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }


    static public Long lPush(String key, Object value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }


    static  public Long lPush(String key, Object value, long time) {
        Long index = redisTemplate.opsForList().rightPush(key, value);
        expire(key, time);
        return index;
    }


    static  public Long lPushAll(String key, Object... values) {
        return redisTemplate.opsForList().rightPushAll(key, values);
    }


    static public Long lPushAll(String key, Long time, Object... values) {
        Long count = redisTemplate.opsForList().rightPushAll(key, values);
        expire(key, time);
        return count;
    }


    static  public Long lRemove(String key, long count, Object value) {
        return redisTemplate.opsForList().remove(key, count, value);
    }
}
