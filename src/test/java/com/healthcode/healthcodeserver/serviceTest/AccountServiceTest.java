package com.healthcode.healthcodeserver.serviceTest;

import com.healthcode.healthcodeserver.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccountServiceTest {
  @Autowired
  AccountService accountService;

  /**
   * 测试函数 Test
   * 用来测试函数    accountService.accountIsValid(String userName , String userPassword , int category);
   * 取值包括
   *   tring userName_0 = "admin";
   *     String userPassword_0 = "123456";
   *     int category_0 = 2;
   *
   *     String userName_1 = "2347fghuws0zt';,u";
   *     String userPassword_1 = "/';.,k289wujszasf";
   *     int category_1 = 349123;
   *
   *     String userName_2 = "李四";
   *     String userPassword_2 = "1";
   *     int category_2 = 1;
   */
  @Test
  public void test(){
    String userName_0 = "admin";
    String userPassword_0 = "123456";
    int category_0 = 2;

    String userName_1 = "2347fghuws0zt';,u";
    String userPassword_1 = "/';.,k289wujszasf";
    int category_1 = 349123;

    String userName_2 = "李四";
    String userPassword_2 = "1";
    int category_2 = 1;

    System.out.println(accountService.accountIsValid(userName_0 , userPassword_0 , category_0 ));
    System.out.println(accountService.accountIsValid(userName_1 , userPassword_1 , category_1));
    System.out.println(accountService.accountIsValid(userName_2 , userPassword_2 , category_2 ));
  }
}
