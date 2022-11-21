package com.healthcode.healthcodeserver.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.healthcode.healthcodeserver.common.Result;
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
  @Autowired
  AbnormalInfoService abnormalInfoService;
  @Autowired
  FamilyBingApplicationService familyBingApplicationService;

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
  public Result getTesterApplicationInfo(@RequestParam("token") String token,
                                         @RequestParam("page") Integer page,
                                         @RequestParam("size") Integer size) {
    log.info("admin with token "+token+" acquire tester apply info");
    if (tokenUtil.verify(token)) {
      log.info("admin with token "+token+" successfully get application list.");
      // 统计未处理的数据条数
      QueryWrapper<IdentityApplication> wrapper = new QueryWrapper<>();
      wrapper.lt("is_processed", 1);
      long tot = identityApplicationService.count(wrapper);

      List<IdentityApplication> identityApplications;
      if (page == null) {
        identityApplications = identityApplicationService.getTesterApplicationList(100);
      } else {
        identityApplications = identityApplicationService.getByPage(page, size);
      }
      return new Result()
              .ok()
              .putData("total", tot)
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
      String resultInfo = request.getString("result_info");
      String applicationId = request.getString("application_id");
      UpdateWrapper<IdentityApplication> wrapper = new UpdateWrapper<>();
      wrapper.eq("application_id", applicationId);
      wrapper.set("is_succeed", isSucceed);
      wrapper.set("is_processed", 1);
      wrapper.set("result_info", resultInfo);
      identityApplicationService.update(wrapper);
      if (isSucceed == 1) {
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

  /**
   *
   * @param token
   * @return
   */
  @GetMapping("/remote_report")
  public Result getRemoteReport(@RequestParam("token") String token,
                                @RequestParam("page") Integer page,
                                @RequestParam("size") Integer size) {
    if (!tokenUtil.verify(token)) {
      return new Result()
              .error(2)
              .message("unknown token");
    }
    log.info("admin with token " + token + " get remote report");
    QueryWrapper<RemoteReporting> wrapper = new QueryWrapper<>();
    wrapper.lt("is_checked", 1);
    long tot = remoteReportingService.count(wrapper);

    List<RemoteReporting> remoteReportings;
    if (page == null) {
      remoteReportings = remoteReportingService.listByLimit(100);
    } else {
      remoteReportings = remoteReportingService.listByPage(page, size);
    }
    return new Result()
            .ok()
            .putData("total", tot)
            .putData("reporting_list", remoteReportings);
  }

  /**
   *
   * @param request
   * @return
   */
  @PostMapping("remote_report")
  public Result postRemoteReport(@RequestBody JSONObject request) {
    String token = request.getString("token");
    if (!tokenUtil.verify(token)) {
      return new Result()
              .error(2)
              .message("unknown token");
    }
    log.info("admin with token " + token + " post remote report");
    Integer isAllowed = request.getInteger("is_allowed");
    String reportId = request.getString("report_id");
    UpdateWrapper<RemoteReporting> wrapper = new UpdateWrapper<>();
    wrapper.eq("report_id", reportId);
    wrapper.set("is_allowed", isAllowed);
    wrapper.set("is_checked", 1);
    remoteReportingService.update(wrapper);
    return new Result().ok();
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

  /**
   * 获取异常信息申诉的列表
   * @param token 用户通信凭证
   * @return Result内容见文档
   */
  @GetMapping("/abnormal")
  public Result getAbnormalInfo(@RequestParam("token") String token,
                                @RequestParam("page") Integer page,
                                @RequestParam("size") Integer size) {
    if (!tokenUtil.verify(token)) {
      return new Result()
              .error(2);
    }
    log.info("admin with token " + token + " get abnormal info");
    QueryWrapper<AbnormalInfo> wrapper = new QueryWrapper<>();
    wrapper.lt("is_investigated", 1);
    long tot = abnormalInfoService.count(wrapper);

    List<AbnormalInfo> abnormalInfos;
    if (page == null) {
      abnormalInfos = abnormalInfoService.listByLimit(100);
    } else {
      abnormalInfos = abnormalInfoService.listByPage(page, size);
    }
    return new Result()
            .ok()
            .putData("total", tot)
            .putData("abnormal_list", abnormalInfos);
  }

  /**
   *
   * @param request
   * @return
   */
  @PostMapping("/abnormal")
  public Result postAbnormalInfo(@RequestBody JSONObject request) {
    String token = request.getString("token");
    if (!tokenUtil.verify(token)) {
      return new Result()
              .error(2);
    }
    log.info("admin with token " + token + " post abnormal info");
    String applicationId = request.getString("application_id");
    Integer isProcessed = request.getInteger("is_processed");
    UpdateWrapper<AbnormalInfo> wrapper = new UpdateWrapper<>();
    wrapper.eq("application_id", applicationId);
    wrapper.set("is_investigated", 1);
    wrapper.set("is_processed", isProcessed);
    abnormalInfoService.update(wrapper);
    return new Result().ok();
  }

  /**
   *
   * @param token
   * @return
   */
  @GetMapping("/family_binding")
  public Result getFamilyBinding(@RequestParam("token") String token,
                                 @RequestParam("page") Integer page,
                                 @RequestParam("size") Integer size) {
    if (!tokenUtil.verify(token)) {
      return new Result()
            .error(2);
    }
    log.info("admin with token " + token + " get family_binding info");
    QueryWrapper<FamilyBingApplication> wrapper = new QueryWrapper<>();
    wrapper.lt("is_processed", 1);
    long tot = familyBingApplicationService.count(wrapper);

    List<FamilyBingApplication> applications;
    if (page == null) {
      applications = familyBingApplicationService.listByLimit(100);
    } else {
      applications = familyBingApplicationService.listByPage(page, size);
    }
    return new Result()
            .ok()
            .putData("total", tot)
            .putData("application_list", applications);
  }


  /**
   *
   * @param request
   * @return
   */
  @PostMapping("/family_binding")
  public Result postFamilyBinding(@RequestBody JSONObject request) {
    String token = request.getString("token");
    if (!tokenUtil.verify(token)) {
      return new Result()
              .error(2);
    }
    String applicationId = request.getString("application_id");
    Integer isSucceed = request.getInteger("is_succeed");
    String resultInfo = request.getString("result_info");
    UpdateWrapper<FamilyBingApplication> wrapper = new UpdateWrapper<>();
    wrapper.eq("application_id", applicationId);
    wrapper.set("is_succeed", isSucceed);
    wrapper.set("is_processed", 1);
    wrapper.set("result_info", resultInfo);
    familyBingApplicationService.update(wrapper);
    return new Result().ok();
  }
}
