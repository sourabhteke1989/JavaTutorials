package com.maxlogic.springboottutorial.redis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@EnableCaching
@Slf4j
public class RedisService {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    @Qualifier("AuthCacheKeyGenerator")
    KeyGenerator keyGenerator;

    @Cacheable(value = "user", keyGenerator = "AuthCacheKeyGenerator")
    public User fetchAuthorizationData(String headerToken, String emailId) {
        log.info("Called fetchAuthorizationData");
        if(headerToken.endsWith("12345")) {
            return new User(1, "sourabh", true);
        } else {
            return new User(2, "pratik", false);
        }
    }

    @Cacheable(value = "user", keyGenerator = "AuthCacheKeyGenerator")
    public User getUserFromCache(String headerToken, String emailId) {
        System.out.println("User not found in cache");
        log.info("User not found in cache");
        return null;
    }

    @CacheEvict(value = "user", keyGenerator = "AuthCacheKeyGenerator")
    public boolean logoutUser(String headerKey,String emailId) {
        log.info("Logout completed, Cache evicted !!");
        return true;
    }

    public Boolean isUserExistsInCache(String headerToken) {
        String key = (String) keyGenerator.generate(null, null, headerToken);
        return redisTemplate.hasKey(key);
    }

}
