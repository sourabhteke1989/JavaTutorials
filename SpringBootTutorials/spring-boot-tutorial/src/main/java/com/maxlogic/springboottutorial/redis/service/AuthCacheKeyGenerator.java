package com.maxlogic.springboottutorial.redis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Slf4j
public class AuthCacheKeyGenerator implements KeyGenerator {
    @Override
    public Object generate(Object target, Method method, Object... params) {
        log.debug("CACHE-PARAM {}",params);
        return params.toString().substring(params.toString().length() - 50);
    }
}
