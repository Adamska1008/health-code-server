package com.healthcode.healthcodeserver.common;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

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
  private Map<String, Object> data;

  public static Result ok() {
    return new Result(){{ setStatusCode(0); setMessage("OK"); }};
  }

  public static Result error() {
    return new Result(){{ setStatusCode(1); setMessage("UNKNOWN ERROR"); }};
  }

  public Result putData(String key, Object value) {
    this.data.put(key, value);
    return this;
  }
}
