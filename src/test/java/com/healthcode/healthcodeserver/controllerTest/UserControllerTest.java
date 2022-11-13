package com.healthcode.healthcodeserver.controllerTest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson2.JSON;
import com.healthcode.healthcodeserver.common.Result;
import com.healthcode.healthcodeserver.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserControllerTest {
  @Autowired
  UserController userController;

  @Test
  public void code2SessionTest() {
    Result result = userController.code2Session("053f6l1w3IEltZ2AoT0w3CyrqA0f6l1R", null);
    System.out.println(JSON.toJSON(result));
  }

  @Test
  public void getMainPageInfoTest() {
  }
  @Test
  public void JSONTest(){
    String personList = "[{name=fy, idNumber=1, phone=1}, {name=fy, idNumber=1, phone=1, time=2022-11-13 15:03:14}]";
    JSONArray jsonArray = com.alibaba.fastjson.JSON.parseArray(personList);
    int size = jsonArray.size();
    for (int i=0;i<size;i++){
      JSONObject jsonObject = jsonArray.getJSONObject(i);
      System.out.println(jsonObject.getString("name"));
    }
  }
}
