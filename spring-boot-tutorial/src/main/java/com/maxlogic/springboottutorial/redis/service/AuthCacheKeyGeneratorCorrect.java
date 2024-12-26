package com.maxlogic.springboottutorial.redis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;

@Slf4j
public class AuthCacheKeyGeneratorCorrect implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {
        log.info("Cache key generation params [{}]", params);
        String token = (String) params[0];
        String key = token.substring(token.length() - 50);
        log.info("Cache key generated [{}]", key);
        return key;
    }
}
