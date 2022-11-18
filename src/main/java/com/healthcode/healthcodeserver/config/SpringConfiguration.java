package com.healthcode.healthcodeserver.config;

import com.alibaba.fastjson2.support.config.FastJsonConfig;
import com.alibaba.fastjson2.support.spring.http.converter.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SpringConfiguration {
  /**
   * RestTemplate 用于发送http请求
   * @return restTemplate bean
   */
  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
