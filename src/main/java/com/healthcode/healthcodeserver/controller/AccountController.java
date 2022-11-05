package com.healthcode.healthcodeserver.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.healthcode.healthcodeserver.common.Result;
import com.healthcode.healthcodeserver.entity.Account;
import com.healthcode.healthcodeserver.entity.IdentityApplication;
import com.healthcode.healthcodeserver.service.AccountService;
import com.healthcode.healthcodeserver.service.IdentityApplicationService;
import com.healthcode.healthcodeserver.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping("web/admin")
public class AccountController {
  @Autowired
  AccountService accountService;
  @Autowired
  TokenUtil tokenUtil;
  @Autowired
  IdentityApplicationService identityApplicationService;

  /**
   * 管理员登陆获取验证token
   * @param username 用户名
   * @param password 密码
   * @return token值
   */
  @GetMapping("/login")
  public Result adminLogIn(@RequestParam("username") String username,
                           @RequestParam("password") String password){
    log.info("Admin with username "+username+" request log in.");
    QueryWrapper<Account> wrapper = new QueryWrapper<>();
    wrapper.eq("username", username);
    Account account = accountService.getOne(wrapper);
    if (account == null) {
      return new Result().error(5);
    }
    if (!account.getPassword().equals(password)) {
      return new Result().error(6);
    }
    String token = tokenUtil.gen(account);
    return new Result().ok()
            .putData("token", token);
  }

  @GetMapping("/tester_apply")
  public Result getTesterApplicationInfo(@RequestParam String token) {
    if (tokenUtil.verify(token)) {
      List<IdentityApplication> identityApplications = identityApplicationService.getTesterApplicationList();
      return new Result().ok()
              .putData("application_list", identityApplications);
    } else {
      return new Result().error(null);
    }
  }
}
