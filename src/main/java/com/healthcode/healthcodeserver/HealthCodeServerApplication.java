package com.healthcode.healthcodeserver;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HealthCodeServerApplication {
  public static void main(String[] args) {
    SpringApplication.run(HealthCodeServerApplication.class, args);
  }
}
