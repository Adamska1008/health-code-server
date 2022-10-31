package com.healthcode.healthcodeserver.common;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

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

  public Result ok() {
    this.setStatusCode(0);
    this.setMessage("OK");
    return this;
  }

  public Result error() {
    this.setStatusCode(1);
    this.setMessage("UNKNOWN ERROR");
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
