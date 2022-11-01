package com.healthcode.healthcodeserver.serviceTest;

import com.healthcode.healthcodeserver.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccountServiceTest {
  @Autowired
  AccountService accountService;
  @Test
  public void test(){
    System.out.println(accountService.accountIsValid("admin","123456",2));
  }
}
