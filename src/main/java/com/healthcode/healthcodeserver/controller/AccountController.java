package com.healthcode.healthcodeserver.controller;

import com.healthcode.healthcodeserver.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AccountController {
  @Autowired
  AccountService accountService;

  //防疫管理人员登陆验证接口
  @PostMapping("/login")
  public boolean adminLogIn(@RequestParam("userName") String userName,
                            @RequestParam("password") String userPassword){
    return accountService.accountIsValid(userName,userPassword,2);
  }
}
