package com.healthcode.healthcodeserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import javax.annotation.Resource;
import java.time.Duration;

//@Configuration
//@EnableCaching // 开启缓存支持，redis需要此注解读取配置文件
//public class RedisConfig {
//  @Value("${spring.redis.timeout}")
//  private Duration timeToLive = Duration.ZERO;
//  @Resource // lettuce 连接池
//  private LettuceConnectionFactory lettuceConnectionFactory;
//}
