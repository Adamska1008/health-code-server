package com.healthcode.healthcodeserver.controllerTest;

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
  public void insertUserInfoTest(){
    String token = "2d783y";
    String userinfo = "y928he81u";
    Result result = userController.insertUserInfo(  token,userinfo);
    System.out.println(JSON.toJSON(result));
  }

  @Test
  public void getMainPageInfoTest() {
    String openid = "a8rgf23r7r4y54y";
    String sessionKey ="f34rat34ter";
    String appid = "d2378y";
    Result result = userController.getMainPageInfo(openid,sessionKey,appid);
    System.out.println(JSON.toJSON(result));
  }

  @Test
  public  void getNucleicAcidTestInfoTest(){
    String openid = "a8rgf23r7r4y54y";
    String sessionKey ="f34rat34ter";
    String appid = "d2378y";
    Result result = userController.getNucleicAcidTestInfo(openid,sessionKey,appid);
    System.out.println(JSON.toJSON(result));
  }

  @Test
  public  void getVaccineInoculationInfoTest(){
    String openid = "a8rgf23r7r4y54y";
    String sessionKey ="f34rat34ter";
    String appid = "d2378y";
    Result result = userController.getVaccineInoculationInfo(openid,sessionKey,appid);
    System.out.println(JSON.toJSON(result));
  }
}
