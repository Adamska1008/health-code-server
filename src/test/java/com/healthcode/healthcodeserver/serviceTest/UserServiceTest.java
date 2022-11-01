package com.healthcode.healthcodeserver.serviceTest;

import com.alibaba.fastjson2.JSON;
import com.healthcode.healthcodeserver.entity.User;
import com.healthcode.healthcodeserver.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {
  @Autowired
  UserService userService;

  @Test
  public void UnitTest() {
    getUserInfoByPersonIdTest("35341719961123514X");
    getUserInfoByOpenId("0");
  }

  @Test
  public void getUserInfoByPersonIdTest(String personId) {
    User user = userService.getUserInfoByPersonId(personId);
    System.out.println(JSON.toJSON(user));
  }

  @Test
  public void getUserInfoByOpenId(String openId) {
    User user = userService.getUserInfoByOpenId(openId);
    System.out.println(JSON.toJSON(user));
  }
}
