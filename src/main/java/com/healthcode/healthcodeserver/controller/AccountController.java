package com.healthcode.healthcodeserver.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.healthcode.healthcodeserver.common.Result;
import com.healthcode.healthcodeserver.entity.Account;
import com.healthcode.healthcodeserver.entity.IdentityApplication;
import com.healthcode.healthcodeserver.entity.Tester;
import com.healthcode.healthcodeserver.service.AccountService;
import com.healthcode.healthcodeserver.service.IdentityApplicationService;
import com.healthcode.healthcodeserver.service.TesterService;
import com.healthcode.healthcodeserver.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping("web/admin")
public class AccountController {
  @Autowired
  AccountService accountService;
  @Autowired
  TesterService testerService;
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
      log.info("User with given username not exist.");
      return new Result().error(201);
    }
    if (!account.getPassword().equals(password)) {
      log.info("Wrong password.");
      return new Result().error(202);
    }
    String token = tokenUtil.gen(account);
    return new Result()
            .ok()
            .putData("token", token);
  }

  /**
   * 后端向前端发送检测人员申请的功能
   * @param token 管理员自身的token
   * @return 检测人员申请列表
   */
  @GetMapping("/get_tester_apply")
  public Result getTesterApplicationInfo(@RequestParam("token") String token) {
    if (tokenUtil.verify(token)) {
      log.info("admin with token "+token+" acquire tester apply info");
      List<IdentityApplication> identityApplications = identityApplicationService.getTesterApplicationList(100);
      return new Result()
              .ok()
              .putData("application_list", identityApplications);
    } else {
      log.info("Invalid token.");
      return new Result()
              .error(null);
    }
  }

  /**
   * 前端向后端返回处理完的申请
   * @param token token
   * @param application 申请条目
   * @return Result
   */
  @PostMapping("/post_tester_apply")
  public Result postTesterApplicationInfo(@RequestParam("token") String token,
                                          @RequestBody IdentityApplication application) {
    if (tokenUtil.verify(token)) {
      identityApplicationService.updateApplicantProcessed(application.getId(), application.getIsSucceed());
      if (application.getIsSucceed() == 1) {
        Tester tester = new Tester(
                application.getOpenId(), application.getApplicantPersonId(),
                application.getApplicantPhone(), application.getApplicantName());
        testerService.save(tester);
      }
      return new Result().ok();
    } else {
      log.info("unknown token");
      return new Result().error(null).message("unknown token");
    }
  }
}
