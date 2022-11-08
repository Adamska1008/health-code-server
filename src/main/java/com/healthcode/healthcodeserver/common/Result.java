package com.healthcode.healthcodeserver.common;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 统一的后端向前端传输数据对象
 * 方法统一使用链式调用
 */
@Getter
@Setter
public class Result {
  /*
  statusCode:
  0: OK
  1: ERROR
   */
  private Integer statusCode;
  private String message;
  private final Map<String, Object> data = new HashMap<>();
  private static final Map<Integer, String> errorCodeToInfo = new HashMap<>(){{
    put(0, "OK");
    put(1, "unknown: Unknown Error");
    put(101, "user: Failed to fetch session key and openid by code");
    put(102, "user: unregistered session_key");
    put(103, "user: user with given openid not existed");
    put(201, "admin: no such account with given username");
    put(202, "admin: wrong password");
  }};

  public Result ok() {
    this.setStatusCode(0);
    this.setMessage("OK");
    return this;
  }

  public Result error(Integer errorCode) {
    this.statusCode = Objects.requireNonNullElse(errorCode, 1);
    this.message = errorCodeToInfo.get(this.statusCode);
    return this;
  }

  public Result message(String message) {
    this.message = message;
    return this;
  }

  /**
   * 将键值对放入Result的Data返回值中
   * @param key 字符串键
   * @param value 数据值
   * @return Result对象本身
   */
  public Result putData(String key, Object value) {
    this.data.put(key, value);
    return this;
  }
}
