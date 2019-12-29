package com.majesco.poc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * @author Biplab Nayak
 */

@Configuration
class Config {

    @Bean
    RedisConnectionFactory connectionFactory() {
        RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration()
                .master("redis-cluster")
                .sentinel("172.17.192.191", 16381) // Sentinel Port
                .sentinel("172.17.192.194", 16380);// Sentinel Port
        return new JedisConnectionFactory(sentinelConfig);
    }

    @Bean
    RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {

        // just used StringRedisTemplate for simplicity here.
        return new StringRedisTemplate(factory);
    }
}
