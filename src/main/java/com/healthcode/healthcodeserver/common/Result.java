package com.healthcode.healthcodeserver.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
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

  public Result putData(String key, Object value) {
    this.data.put(key, value);
    return this;
  }
}
