package com.prossafy101.ohnaco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;

@Component
public class RedisUtil {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public String getData(String key) {
        ValueOperations<String, String> vop = redisTemplate.opsForValue();
        return vop.get(key);
    }

    public void setData(String key, String value, long expireTime) {
        ValueOperations<String, String> vop = redisTemplate.opsForValue();
        Duration expire = Duration.ofMillis(expireTime);
        vop.set(key, value, expire);
    }

    public void setScrapData(String key, String articleid) {
        ListOperations<String, String> vop = redisTemplate.opsForList();
        vop.rightPush(key, articleid);
    }

    public Long removeScrapData(String key, String articleid) {
        ListOperations<String, String> vop = redisTemplate.opsForList();
        return vop.remove(key, 0, articleid);
    }

    public boolean getScrapUseridData (String key, String articleid) {
        ListOperations<String, String> vop = redisTemplate.opsForList();
        return vop.indexOf(key, articleid)==null?false:true;
    }

    public List<String> getScrapData (String key, int start, int end) {
        ListOperations<String, String> vop = redisTemplate.opsForList();
        return vop.range(key, start, end);
    }

    public Long getScrapSizeData (String key) {
        ListOperations<String, String> vop = redisTemplate.opsForList();
        return vop.size(key);
    }

    public void setVisitData(String key) {
        ValueOperations<String, String> vop = redisTemplate.opsForValue();
        vop.increment(key, 1);
    }

    public boolean getLikeUseridData(String key, String userid) {
        SetOperations<String, String> vop = redisTemplate.opsForSet();
        return vop.isMember(key, userid);
    }

    public Long getLikeCountData(String key) {
        SetOperations<String, String> vop = redisTemplate.opsForSet();
        return vop.size(key);
    }

    public Long delLikeUserData(String key, String userid) {
        SetOperations<String, String> vop = redisTemplate.opsForSet();
        return vop.remove(key, userid);
    }

    public void setLikeData(String key, String value) {
        SetOperations<String, String> vop = redisTemplate.opsForSet();
        vop.add(key, value);
    }

    public void deleteData(String key) {
        redisTemplate.delete(key);
    }
}
