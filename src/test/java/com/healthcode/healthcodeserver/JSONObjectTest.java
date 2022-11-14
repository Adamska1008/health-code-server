package com.healthcode.healthcodeserver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

public class JSONObjectTest {
  @Test
  public void unitTest() {
    JSONObject object = JSONObject.parseObject("{}");
    System.out.println(object.getString("phoneNumber"));
  }
}
