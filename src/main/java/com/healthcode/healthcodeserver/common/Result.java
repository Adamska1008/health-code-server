package com.healthcode.healthcodeserver.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 统一的后端向前端传输数据对象
 * 方法统一使用链式调用
 */
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
    put(2, "invalid token");
    put(3, "miss correct openid");
    put(101, "user: Failed to fetch session key and openid by code");
    put(102, "user: unregistered session_key");
    put(103, "user: user with given openid not existed");
    put(201, "admin: no such account with given username");
    put(202, "admin: wrong password");
  }};

  /**
   * 链式调用，设置ok返回值，返回对象本身
   * @return 修改完的对象本身
   */
  public Result ok() {
    this.setStatusCode(0);
    this.setMessage("OK");
    return this;
  }

  /**
   * 链式调用，设置error返回值，返回对象本身
   * @return 修改完的对象本身
   */
  public Result error(Integer errorCode) {
    this.statusCode = Objects.requireNonNullElse(errorCode, 1);
    this.message = getErrorCodeToInfo().get(this.statusCode);
    return this;
  }

  /**
   * 链式调用，设置message返回值，返回对象本身
   * @return 修改完的对象本身
   */
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

  public Map<String, Object> getData() {
    return data;
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public void setStatusCode(Integer statusCode) {
    this.statusCode = statusCode;
  }

  public Integer getStatusCode() {
    return statusCode;
  }

  public static Map<Integer, String> getErrorCodeToInfo() {
    return errorCodeToInfo;
  }

  @Override
  public String toString() {
    return "Result{" +
            "statusCode=" + statusCode +
            ", message='" + message + '\'' +
            ", data=" + data +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Result result = (Result) o;
    return Objects.equals(statusCode, result.statusCode)
            && Objects.equals(message, result.message)
            && Objects.equals(data, result.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(statusCode, message, data);
  }
}
