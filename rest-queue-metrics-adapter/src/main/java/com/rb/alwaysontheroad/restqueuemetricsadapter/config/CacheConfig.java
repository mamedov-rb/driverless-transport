package com.rb.alwaysontheroad.restqueuemetricsadapter.config;

import com.rb.alwaysontheroad.restqueuemetricsadapter.data.dto.TransportDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import static io.lettuce.core.ReadFrom.REPLICA_PREFERRED;

@Configuration
public class CacheConfig {

    @Value("${spring.data.redis.host}")
    private String host;
    @Value("${spring.data.redis.port}")
    private Integer port;

    @Bean
    public RedisTemplate<String, TransportDto> redisTemplate() {
        RedisTemplate<String, TransportDto> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());
        return template;
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(
                new RedisStandaloneConfiguration(host, port),
                LettuceClientConfiguration.builder().readFrom(REPLICA_PREFERRED).build()
        );
    }
}
