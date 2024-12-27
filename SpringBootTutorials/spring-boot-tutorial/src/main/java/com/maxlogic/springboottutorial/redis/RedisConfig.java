package com.maxlogic.springboottutorial.redis;

import com.maxlogic.springboottutorial.redis.service.AuthCacheKeyGeneratorCorrect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.cache.interceptor.KeyGenerator;

@Configuration
public class RedisConfig {

    @Bean
    public KeyGenerator AuthCacheKeyGenerator() {
        return new AuthCacheKeyGeneratorCorrect();
    }
}
