package com.healthcode.healthcodeserver.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.healthcode.healthcodeserver.common.Result;
import com.healthcode.healthcodeserver.dao.VenueCodeInfoDao;
import com.healthcode.healthcodeserver.entity.*;
import com.healthcode.healthcodeserver.service.*;
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

import java.util.Comparator;
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
  @Autowired
  UserService userService;
  @Autowired
  ItineraryInfoService itineraryInfoService;
  @Autowired
  VenueCodeInfoService venueCodeInfoService;
  @Autowired
  RemoteReportingService remoteReportingService;

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
    log.info("Admin with username "+username+" successfully log in.");
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
    log.info("admin with token "+token+" acquire tester apply info");
    if (tokenUtil.verify(token)) {
      log.info("admin with token "+token+" successfully get application list.");
      List<IdentityApplication> identityApplications = identityApplicationService.getTesterApplicationList(100);
      return new Result()
              .ok()
              .putData("application_list", identityApplications);
    } else {
      log.info("Invalid token.");
      return new Result()
              .error(2);
    }
  }

  /**
   * 前端向后端返回处理完的检测人员申请
   * @Param request 请求
   * @return Result
   */
  @PostMapping("/post_tester_apply")
  public Result postTesterApplicationInfo(@RequestBody JSONObject request) {
    String token = request.getString("token");
    if (tokenUtil.verify(token)) {
      log.info("Account with token " + token + " post processed application.");
      Integer isSucceed = request.getInteger("is_succeed");
      String resultInfo = request.getString("resultInfo");
      String applicationId = request.getString("application_id");
      UpdateWrapper<IdentityApplication> wrapper = new UpdateWrapper<>();
      wrapper.eq("application_id", applicationId);
      wrapper.set("is_succeed", isSucceed);
      wrapper.set("result_info", resultInfo);
      identityApplicationService.update(wrapper);
      if (isSucceed == 0) {
        Tester tester = new Tester(
                request.getString("openid"),
                request.getString("applicant_person_id"),
                request.getString("applicant_phone"),
                request.getString("applicant_name")
        );
        testerService.save(tester);
      }
      return new Result()
              .ok();
    } else {
      log.info("unknown token");
      return new Result()
              .error(2)
              .message("unknown token");
    }
  }

  @GetMapping("/remote_report")
  public Result getRemoteReport(@RequestParam("token") String token) {
    if (!tokenUtil.verify("token")) {
      return new Result()
              .error(2)
              .message("unknown token");
    }
    log.info("admin with token " + token + " get remote report");
    List<RemoteReporting> remoteReportings = remoteReportingService.list();
    return new Result()
            .ok()
            .putData("reporting_list", remoteReportings);
  }

  /**
   * 获取指定身份证号码用户的行程信息
   * @param token 用户通信凭证
   * @param personId 身份证号
   * @return Result内容参考文档
   */
  @GetMapping("/itinerary")
  public Result getItinerary(@RequestParam("token") String token,
                             @RequestParam("person_id") String personId) {
    if (!tokenUtil.verify(token)) {
      return new Result()
              .error(2)
              .message("unknown token");
    }
    Result result = new Result().ok();
    User user = userService.getUserInfoByPersonId(personId);
    QueryWrapper<ItineraryInfo> wrapper = new QueryWrapper<>();
    JSONArray jsonArray = new JSONArray();
    wrapper.eq("person_id", personId);
    List<ItineraryInfo> itineraryInfos = itineraryInfoService.list(wrapper);
    itineraryInfos.sort(Comparator.comparing(ItineraryInfo::getRecordTime));
    for (var itineraryInfo : itineraryInfos) {
      JSONObject object = new JSONObject();
      object.put("person_name", user.getName());
      String venueName = venueCodeInfoService.getVenueNameById(itineraryInfo.getVenueId());
      object.put("venue_name", venueName);
      object.put("record_time", itineraryInfo.getRecordTime());
      jsonArray.add(object);
    }
    return result.putData("itinerary_info", jsonArray);
  }


}
