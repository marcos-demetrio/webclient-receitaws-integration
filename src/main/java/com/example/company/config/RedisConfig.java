package com.example.company.config;

import io.lettuce.core.api.reactive.RedisReactiveCommands;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {
  @Value("${app.datasource.redis.host}")
  private String host;

  @Value("${app.datasource.redis.port}")
  private int port;

  @Bean
  public RedisConnection redisConnection() {
    return new RedisConnection(host, Integer.toString(port));
  }

  @Bean
  public RedisReactiveCommands<String, String> redisReactiveCommands() {
    return redisConnection().getRedisConnection().reactive();
  }

  @PreDestroy
  public void close() {
    redisConnection().stop();
  }
}
