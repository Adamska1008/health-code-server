package com.healthcode.healthcodeserver.controllerTest;

import com.alibaba.fastjson2.JSON;
import com.healthcode.healthcodeserver.common.Result;
import com.healthcode.healthcodeserver.controller.AccountController;
import com.healthcode.healthcodeserver.entity.IdentityApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccountControllerTest {
  @Autowired
    AccountController accountController;

  /**
   * 测试函数 adminLoginTest
   * 用来测试函数    accountController.adminLogIn(String username,String password);
   * 用户名及密码的取值包括
   * anlang 123456
   * null 123456
   * anlang null
   * null null
   * wangwu 81923ey13d278re23u8r
   * 李四 8d7q3h12e
   */
  @Test
    public void adminLoginTest(){
    String username_0 = "anlang";
    String password_0 = "123456";
    String username_1 = "wangwu";
    String password_1 = "81923ey13d278re23u8r";
    String username_2 = "李四";
    String password_2 = "8d7q3h12e";
    Result result_0 = accountController.adminLogIn(username_0,password_0);
    System.out.println(JSON.toJSON(result_0));
    Result result_1 = accountController.adminLogIn(null,password_0);
    System.out.println(JSON.toJSON(result_1));
    Result result_2 = accountController.adminLogIn(username_0,null);
    System.out.println(JSON.toJSON(result_2));
    Result result_3 = accountController.adminLogIn(null,null);
    System.out.println(JSON.toJSON(result_3));
    Result result_4 = accountController.adminLogIn(username_1,password_1);
    System.out.println(JSON.toJSON(result_4));
    Result result_5 = accountController.adminLogIn(username_2,password_2);
    System.out.println(JSON.toJSON(result_5));
  }
}
