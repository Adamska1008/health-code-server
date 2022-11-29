package com.healthcode.healthcodeserver.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.healthcode.healthcodeserver.common.Result;
import com.healthcode.healthcodeserver.entity.*;
import com.healthcode.healthcodeserver.service.*;
import com.healthcode.healthcodeserver.util.RedisUtil;
import com.healthcode.healthcodeserver.util.WxUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/wx/user")
public class UserController {
  @Autowired
  UserService userService;
  @Autowired
  VaccineInoculationInfoService vaccineInoculationInfoService;
  @Autowired
  NucleicAcidTestInfoService nucleicAcidTestInfoService;
  @Autowired
  CovidTestInstitutionService covidTestInstitutionService;
  @Autowired
  WxUtil wxUtil;
  @Autowired
  VenueCodeApplicationService venueCodeApplicationService;
  @Autowired
  FamilyBingApplicationService familyBingApplicationService;
  @Autowired
  UserRelationService userRelationService;
  @Autowired
  ItineraryInfoService itineraryInfoService;
  @Autowired
  RemoteReportingService remoteReportingService;
  @Autowired
  VenueCodeInfoService venueCodeInfoService;
  @Autowired
  AbnormalInfoService abnormalInfoService;
  @Autowired
  RegionalRiskProfileService regionalRiskProfileService;
  @Autowired
  RedisUtil redisUtil;

  Map<String, String> openIdToSessionKey = new HashMap<>();

  /**
   * 固定检查session_key 是否合法的程序
   * @param openId openid
   * @param sessionKey session_key
   * @return 如果无openid或open_id不包含对应的session_key返回错误，否则返回ok
   */
  private Result verifySession(String openId, String sessionKey) {
    if (!openIdToSessionKey.containsKey(openId)) {
      log.warn("do not have given openid: "+ openId);
      return new Result().error(null);
    }
    if (!openIdToSessionKey.get(openId).equals(sessionKey)) {
      log.warn("receive openid "+openId+" and session_key "+sessionKey+", which do not correspond");
      return new Result().error(3);
    }
    return new Result().ok();
  }

  /**
   * 前端发送登陆凭证code，后端通过微信接口获取session_key与openid返回给前端
   * 并且将openid与session信息注册到系统内部
   * @param code 登陆批准
   * @param appid 小程序码，
   * @return Result
   */
  @GetMapping("/{appid}/login")
  public Result code2Session(@RequestParam("code") String code,
                             @PathVariable String appid) {
    String data = wxUtil.code2Session(code, 0);
    JSONObject jsonObject = JSONObject.parseObject(data);
    log.info("request to build session with code "+ code);
    String sessionKey = jsonObject.getString("session_key");
    String openId = jsonObject.getString("openid");
    Result result = new Result();
    if (sessionKey == null || openId == null) {
      log.warn("failed to acquire session key.");
      return result.error(2);
    } else {
      jsonObject.forEach(result::putData);
      openIdToSessionKey.put(openId, sessionKey);
      log.info("binding openid " + openId + " to session " + sessionKey + ".");
      return result.ok();
    }
  }

  /**
   * 第一次登录需要填写表单以获取用户信息
   * 用户信息存在userInfo中，openId和sessionKey存在token中
   * @param token token
   * @param userInfo
   * @return
   */
  @PostMapping("/{appid}/info")
  public Result insertUserInfo(@RequestParam("token") String token,
                               @RequestParam("userInfo") String userInfo){
    Result result = new Result();
    JSONObject jsonObject1 = JSONObject.parseObject(userInfo);
    String personName = jsonObject1.getString("personName");
    String personId = jsonObject1.getString("personId");
    String gender = jsonObject1.getString("gender");
    String phoneNumber = jsonObject1.getString("phone");
    JSONObject jsonObject2 = JSON.parseObject(token);
    String openId = jsonObject2.getString("openId");
    int insertResult = userService.insertUserInfo(personId,personName,phoneNumber,openId,gender);
    if (insertResult==1){
      result.ok();
    } else {
      result.error(1);
    }
    return result;
  }

  /**
   * 修改用户信息
   * @param request 请求内容，键值对集合
   * @return Result内容见文档
   */
  @PostMapping("/register_user_info")
  public Result registerUserInfo(@RequestBody JSONObject request) {
    String openId = request.getString("openid");
    String sessionKey = request.getString("session_key");
    Result verifiedResult = verifySession(openId, sessionKey);
    if (verifiedResult.getStatusCode() != 0) {
      return verifiedResult;
    }
    log.info("register new user with openid " + openId);
    User user = new User(
            request.getString("person_id"),
            request.getString("person_name"),
            request.getString("phone_number"),
            openId,
            request.getShort("person_gender"),
            0
    );
    return new Result().ok();
  }

  /**
   * 前端发送openid与session_key获取用户信息。
   * 检查信息无误之后返回为前端定制的信息
   * @param openId 微信用户在小程序上的唯一id
   * @param sessionKey 建立session的凭证
   * @param appid 小程序码
   * @return Result
   */
  @GetMapping("/{appid}/index_profile")
  public Result getMainPageInfo(@RequestParam("openid") String openId,
                                @RequestParam("session_key") String sessionKey,
                                @PathVariable("appid") String appid) {
    Result verifiedResult = verifySession(openId, sessionKey);
    if (verifiedResult.getStatusCode() != 0) {
      return verifiedResult;
    }
    User user = userService.getByOpenId(openId);
    if (user == null) {
      log.warn("no user with such openid");
      return new Result().error(4);
    }
    NucleicAcidTestInfo latestInfo =
            nucleicAcidTestInfoService.getLatestTestInfoByPersonId(user.getPersonId());
    JSONObject latestTest = new JSONObject();
    if (latestInfo != null) {
      String testInstitutionName =
              covidTestInstitutionService.getNameById(latestInfo.getTestInstitutionId());
      latestTest.put("result", latestInfo.getTestResult());
      latestTest.put("test_time", latestInfo.getTestTime());
      latestTest.put("institution_name", testInstitutionName);
    }
    List<VaccineInoculationInfo> vaccineInoculationInfoList =
            vaccineInoculationInfoService.getInfoListByPersonId(user.getPersonId());

    log.info("return info of user with openid "+openId);
    return new Result().ok()
            .putData("name", user.getName())
            .putData("person_id", user.getPersonId())
            .putData("phone_number", user.getPhoneNumber())
            .putData("position", user.getPosition())
            .putData("health_code_color", user.getHealthCodeColor())
            .putData("latest_test", latestTest)
            .putData("vaccine_inoculation_info", vaccineInoculationInfoList);
  }

  /**
   *
   * @param openId 微信用户openid
   * @param sessionKey 会话密钥
   * @param phoneNumber 需要i需改的手机号
   * @return Result内容见文档
   */
  @PostMapping("/change_info")
  public Result changeUserInfo(@RequestParam("openid") String openId,
                               @RequestParam("session_key") String sessionKey,
                               @RequestParam("phone_number") String phoneNumber) {
    Result verifiedResult = verifySession(openId, sessionKey);
    if (verifiedResult.getStatusCode() != 0) {
      return verifiedResult;
    }
    log.info("user with openid" + openId + " change info");
    User user = userService.getByOpenId(openId);
    String personId = user.getPersonId();
    UpdateWrapper<User> wrapper = new UpdateWrapper<>();
    wrapper.eq("person_id", personId);
    if (phoneNumber != null) {
      wrapper.set("phone_number", phoneNumber);
    }
    userService.update(wrapper);
    return new Result().ok();
  }

  /**
   * 获取指定用户的核酸检测信息
   * @param openId 个人openid
   * @param sessionKey 会话密钥
   * @param appid 小程序id
   * @return Result，包含检测信息列表
   */
  @GetMapping("{appid}/nucleic_test_info")
  public Result getNucleicAcidTestInfo(@RequestParam("openid") String openId,
                                       @RequestParam("session_key") String sessionKey,
                                       @PathVariable String appid) {
    Result verifiedResult = verifySession(openId, sessionKey);
    if (verifiedResult.getStatusCode() != 0) {
      return verifiedResult;
    }
    User user = userService.getByOpenId(openId);
    if (user == null) {
      log.warn("no user with such openid");
      return new Result()
              .error(4);
    }
    List<NucleicAcidTestInfo> nucleicAcidTestInfos =
            nucleicAcidTestInfoService.getNucleicAcidTestInfoListByPersonId(user.getPersonId());
    JSONArray infoList = new JSONArray();
    for (var info : nucleicAcidTestInfos) {
      JSONObject jsonInfo = new JSONObject();
      jsonInfo.put("person_id", info.getPersonId());
      jsonInfo.put("test_result", info.getTestResult());
      jsonInfo.put("test_time", info.getTestTime());
      jsonInfo.put("transfer_code", info.getTransferCode());
      CovidTestInstitution covidTestInstitution =
              covidTestInstitutionService.getById(info.getTestInstitutionId());
      jsonInfo.put("institution_name", covidTestInstitution.getName());
      infoList.add(jsonInfo);
    }
    return new Result().ok()
            .putData("info_list", infoList);
  }

  /**
   * 获取个人疫苗记录
   * @param openId 个人openid
   * @param sessionKey 会话密钥
   * @param appid 小程序id
   * @return Result，包含疫苗记录列表
   */
  @GetMapping("{appid}/vaccine_inocu_info")
  public Result getVaccineInoculationInfo(@RequestParam("openid") String openId,
                                          @RequestParam("session_key") String sessionKey,
                                          @PathVariable String appid) {
    Result verifiedResult = verifySession(openId, sessionKey);
    if (verifiedResult.getStatusCode() != 0) {
      return verifiedResult;
    }
    User user = userService.getByOpenId(openId);
    if (user == null) {
      log.warn("no user with such openid");
      return new Result()
              .error(103);
    }
    List<VaccineInoculationInfo> vaccineInoculationInfos =
            vaccineInoculationInfoService.getInfoListByPersonId(user.getPersonId());
    return new Result()
            .ok()
            .putData("info_list", vaccineInoculationInfos);
  }

  /**
   * 申请场所码
   * @param request http请求体
   * @return 申请提交成功的result
   */
  @PostMapping("/venue_code")
  public Result applyVenueCode(@RequestBody JSONObject request) {
    String openId = request.getString("openid");
    String sessionKey = request.getString("session_key");
    Result verifiedResult = verifySession(openId, sessionKey);
    if (verifiedResult.getStatusCode() != 0) {
      return verifiedResult;
    }
    VenueCodeApplication application = new VenueCodeApplication(
            null,
            request.getString("applicant_name"),
            request.getString("applicant_person_id"),
            request.getString("position"),
            request.getString("location"),
            request.getString("type"),
            request.getString("place_name"),
            0, 0, null, null
    );
    venueCodeApplicationService.save(application);
    redisUtil.addVenueCodeApplication(openId, application.getId());
    return new Result()
            .ok()
            .putData("application_id", application.getId());
  }


  /**
   * 获取场所码申请的状态
   * @param openId 用户openid
   * @param sessionKey 用户session_key
   * @param applicationId 申请id
   * @return 详情见文档
   */
  @GetMapping("/venue_code")
  public Result checkVenueCode(@RequestParam("openid") String openId,
                               @RequestParam("session_key") String sessionKey,
                               @RequestParam("application_id") String applicationId) {
    Result verifiedResult = verifySession(openId, sessionKey);
    if (verifiedResult.getStatusCode() != 0) {
      return verifiedResult;
    }
    VenueCodeApplication application = venueCodeApplicationService.getById(applicationId);
    if (application.getIsPassed() == 1) {
      return new Result()
              .ok()
              .putData("is_solved", application.getIsSolved())
              .putData("is_passed", application.getIsPassed())
              .putData("venue_id", application.getVenueId())
              .putData("venue_type", application.getType())
              .putData("place_name", application.getPlaceName())
              .putData("position", application.getPosition())
              .putData("location", application.getLocation());
    } else {
      return new Result()
              .ok()
              .putData("is_solved", application.getIsSolved())
              .putData("is_passed", application.getIsPassed())
              .putData("result_info", application.getResultInfo());
    }
  }

  /**
   *
   * @param openId
   * @param sessionKey
   * @return
   */
  @GetMapping("/venue_code/id_list")
  public Result getVenueCodeApplicationIdList(@RequestParam("openid") String openId,
                                              @RequestParam("session_key") String sessionKey) {
    Result verifiedResult = verifySession(openId, sessionKey);
    if (verifiedResult.getStatusCode() != 0) {
      return verifiedResult;
    }
    log.info("User with openid " + openId + " acquire venue code application id list.");
    List<String> idList = redisUtil.getVenueCodeApplicationIdList(openId);
    return new Result()
            .ok()
            .putData("id_list",idList);
  }

  /**
   * 扫描场所码，将场所码+person_id+时间戳包装成一个行程信息保存到数据库
   * @param openId 用户openid
   * @param sessionKey 会话密钥
   * @param venueId 场所码id
   * @return Result内容见文档
   */
  @PostMapping("/scan_venue_code")
  public Result scanVenueCode(@RequestParam("openid") String openId,
                              @RequestParam("session_key") String sessionKey,
                              @RequestParam("venue_id") String venueId) {
    Result verifiedResult = verifySession(openId, sessionKey);
    if (verifiedResult.getStatusCode() != 0) {
      return verifiedResult;
    }
    User user = userService.getByOpenId(openId);
    String personId = user.getPersonId();
    ItineraryInfo itineraryInfo =
            new ItineraryInfo(personId, venueId, new Timestamp(System.currentTimeMillis()));
    itineraryInfoService.save(itineraryInfo);
    VenueCodeInfo info = venueCodeInfoService.getById(venueId);
    return new Result()
            .ok()
            .putData("venue_id", info.getId())
            .putData("place_name", info.getName())
            .putData("type", info.getType())
            .putData("location", info.getLocation())
            .putData("position", info.getPosition());
  }

  /**
   * 绑定家属健康码申请
   * @param request POST请求的请求体
   * @return 返回的Result data 中包含申请id，前端需要保存此id以用于查询申请的结果
   */
  @PostMapping("/family_binding")
  public Result applyFamilyBinding(@RequestBody JSONObject request) {
    String openId = request.getString("openid");
    String sessionKey = request.getString("session_key");
    Result verifiedResult = verifySession(openId, sessionKey);
    if (verifiedResult.getStatusCode() != 0) {
      return verifiedResult;
    }
    FamilyBingApplication application = new FamilyBingApplication(
            null,
            request.getString("applicant_name"),
            request.getString("applicant_person_id"),
            request.getString("relative_name"),
            request.getString("relative_person_id"),
            request.getString("additional_info"),
            request.getInteger("relation_type"),
            0, 0, null
    );
    familyBingApplicationService.save(application);
    redisUtil.addFamilyBindingApplication(openId, application.getId());
    return new Result()
            .ok()
            .putData("application_id", application.getId());
  }

  /**
   * 查看绑定家属健康码的申请结果
   * @param openId 用户小程序openid
   * @param sessionKey 会话密钥
   * @param id 申请id
   * @return Result内容说明见文档
   */
  @GetMapping("/family_binding")
  public Result checkFamilyBindingApplication(@RequestParam("openid") String openId,
                                              @RequestParam("session_key") String sessionKey,
                                              @RequestParam("application_id") String id) {
    Result verifiedResult = verifySession(openId, sessionKey);
    if (verifiedResult.getStatusCode() != 0) {
      return verifiedResult;
    }
    FamilyBingApplication application = familyBingApplicationService.getById(id);
    if (application == null) {
      return new Result().error(null).message("no such application with given application id");
    }
    return new Result()
            .ok()
            .putData("processed", application.getIsProcessed())
            .putData("succeed", application.getIsSucceed())
            .putData("relative_name",application.getRelativeName())
            .putData("relative_person_id",application.getRelativePersonId());
  }

  /**
   *
   * @param openId
   * @param sessionKey
   * @return
   */
  @GetMapping("/family/id_list")
  public Result getFamilyBindingIdList(@RequestParam("openid") String openId,
                                       @RequestParam("session_key") String sessionKey) {
    Result verifiedResult = verifySession(openId, sessionKey);
    if (verifiedResult.getStatusCode() != 0) {
      return verifiedResult;
    }
    log.info("User with openid " + openId + " acquire family binding id list.");
    List<String> idList = redisUtil.getFamilyBindingApplicationIdList(openId);
    return new Result()
            .ok()
            .putData("id_list", idList);
  }

  /**
   * 查看家属健康码信息，
   * @param openId 用户小程序openid
   * @param sessionKey 会话密钥
   * @param personId 被查询用户的身份证号
   * @return 数据类似于个人主界面信息，详情见文档
   */
  @GetMapping("/family_profile")
  public Result getFamilyPageInfo(@RequestParam("openid") String openId,
                                  @RequestParam("session_key") String sessionKey,
                                  @RequestParam("person_id") String personId) {
    Result verifiedResult = verifySession(openId, sessionKey);
    if (verifiedResult.getStatusCode() != 0) {
      return verifiedResult;
    }
    User user = userService.getByOpenId(openId);
    UserRelation relation = userRelationService.getRelationByTwoIds(user.getPersonId(), personId);
    if (relation == null) {
      return new Result().error(null).message("Unregistered relation.");
    }
    user = userService.getByPersonId(personId);
    NucleicAcidTestInfo latestInfo =
            nucleicAcidTestInfoService.getLatestTestInfoByPersonId(personId);
    String testInstitutionName =
            covidTestInstitutionService.getNameById(latestInfo.getTestInstitutionId());

    JSONObject latestTest = new JSONObject();
    latestTest.put("result", latestInfo.getTestResult());
    latestTest.put("test_time", latestInfo.getTestTime());
    latestTest.put("institution_name", testInstitutionName);

    List<VaccineInoculationInfo> vaccineInoculationInfoList =
            vaccineInoculationInfoService.getInfoListByPersonId(personId);

    log.info("return info of user with openid "+openId);
    return new Result().ok()
            .putData("name", user.getName())
            .putData("person_id", user.getPersonId())
            .putData("health_code_color", user.getHealthCodeColor())
            .putData("latest_test", latestTest)
            .putData("vaccine_inoculation_info", vaccineInoculationInfoList);
  }

  /**
   * 传参content-type为application/json
   * @param params JSON格式消息体
   * @return Result含义见文档
   */
  @PostMapping("/remote_report")
  public Result remoteReport(@RequestBody JSONObject params) {
    String openId = params.getString("openid");
    String sessionKey = params.getString("session_key");
    Result verifiedResult = verifySession(openId, sessionKey);
    if (verifiedResult.getStatusCode() != 0) {
      return verifiedResult;
    }
    log.info("User with openid " + openId + " post remote report.");
    String personName = params.getString("person_name");
    String personId = params.getString("person_id");
    String from = params.getString("from");
    String to = params.getString("to");
    RemoteReporting reporting = new RemoteReporting(
            null, personName, personId, "",
            from, to, null, (short) 0, (short) 0
    );
    remoteReportingService.save(reporting);
    redisUtil.addRemoteReport(openId, reporting.getId());
    return new Result()
            .ok()
            .putData("report_id", reporting.getId());
  }

  /**
   * 查看异地报备的结果，是否允许
   * @param openId 用户openid
   * @param sessionKey 会话密钥
   * @param reportId 唯一标识报备的id
   * @return Result含义见文档
   */
  @GetMapping("/remote_report")
  public Result checkRemoteReporting(@RequestParam("openid") String openId,
                                     @RequestParam("session_key") String sessionKey,
                                     @RequestParam("report_id") String reportId) {
    Result verifiedResult = verifySession(openId, sessionKey);
    if (verifiedResult.getStatusCode() != 0) {
      return verifiedResult;
    }
    log.info("User with openid " + openId + " check remote report.");
    RemoteReporting reporting = remoteReportingService.getById(reportId);
    if (reporting == null) {
      log.info("Do not have given report with report id " + reportId + ".");
      return new Result().error(null).message("wrong report id");
    }
    Short isChecked = reporting.getIsChecked();
    Short isAllowed = reporting.getIsAllowed();
    String personName = reporting.getPersonName();
    String from = reporting.getFrom();
    String to = reporting.getTo();
    return new Result()
            .ok()
            .putData("is_checked", isChecked)
            .putData("is_allowed", isAllowed)
            .putData("person_name",personName)
            .putData("from",from)
            .putData("to",to);
  }

  /**
   *
   * @param openId
   * @param sessionKey
   * @return
   */
  @GetMapping("/remote_report/id_list")
  public Result getRemoteReportIdList(@RequestParam("openid") String openId,
                                      @RequestParam("session_key") String sessionKey) {
    Result verifiedResult = verifySession(openId, sessionKey);
    if (verifiedResult.getStatusCode() != 0) {
      return verifiedResult;
    }
    log.info("User with openid " + openId + " acquire remote report id list.");
    List<String> idList = redisUtil.getRemoteReportIdList(openId);
    return new Result()
            .ok()
            .putData("id_list", idList);
  }

  /**
   * 提交异常申诉
   * @param request 请求体中的内容
   * @return Result 内容见文档
   */
  @PostMapping("/abnormal")
  public Result postAbnormalInfo(@RequestBody JSONObject request) {
    String openId = request.getString("openid");
    String sessionKey = request.getString("sessionKey");
    Result verifiedResult = verifySession(openId, sessionKey);
    if (verifiedResult.getStatusCode() != 0) {
      return verifiedResult;
    }
    log.info("User with openid " + openId + " post abnormal info application.");
    AbnormalInfo info = new AbnormalInfo(
      null,
      request.getString("person_name"),
      request.getString("person_id"),
      request.getString("phone_number"),
      request.getString("additional_info"),
      request.getString("type"),
      (short)0, (short)0
    );
    abnormalInfoService.save(info);
    return new Result()
            .ok()
            .putData("application_id", info.getId());
  }

  /**
   * 查询异常申诉
   * @param openId 用户小程序open_id
   * @param sessionKey 会话密钥
   * @param applicationId 申诉id
   * @return Result 内容见文档
   */
  @GetMapping("/abnormal")
  public Result checkAbnormalInfo(@RequestParam("openid") String openId,
                                  @RequestParam("session_key") String sessionKey,
                                  @RequestParam("application_id") String applicationId ) {
    Result verifiedResult = verifySession(openId, sessionKey);
    if (verifiedResult.getStatusCode() != 0) {
      return verifiedResult;
    }
    log.info("User with openid " + openId + " check abnormal info application.");
    AbnormalInfo info = abnormalInfoService.getById(applicationId);
    Short isInvestigated = info.getIsInvestigated();
    Short isProcessed = info.getIsProcessed();
    return new Result()
            .ok()
            .putData("is_investigated", isInvestigated)
            .putData("is_processed", isProcessed);
  }

  /**
   *
   * @param openId
   * @param sessionKey
   * @return
   */
  @GetMapping("/abnormal/id_list")
  public Result getAbnormalInfoIdList(@RequestParam("openid") String openId,
                                      @RequestParam("session_key") String sessionKey) {
    Result verifiedResult = verifySession(openId, sessionKey);
    if (verifiedResult.getStatusCode() != 0) {
      return verifiedResult;
    }
    log.info("User with openid " + openId + " acquire abnormal info id list.");
    List<String> idList = redisUtil.getAbnormalInfoIdList(openId);
    return new Result()
            .ok()
            .putData("id_list", idList);
  }

  /**
   *
   * @param openId
   * @param sessionKey
   * @param province
   * @param city
   * @param district
   * @return
   */
  @GetMapping("/risk/specific")
  public Result getSpecificAreaSituation(@RequestParam("openid") String openId,
                                         @RequestParam("session_key") String sessionKey,
                                         @RequestParam("province") String province,
                                         @RequestParam("city") String city,
                                         @RequestParam("district") String district) {
    Result verifiedResult = verifySession(openId, sessionKey);
    if (verifiedResult.getStatusCode() != 0) {
      return verifiedResult;
    }
    log.info("User with openid " + openId + " acquire specific area risk profile");
    int riskLevel = regionalRiskProfileService.getRiskLevel(province, city, district);
    if (riskLevel == -1) {
      return new Result().error(null).message("Position not exists.");
    } else {
      return new Result().ok().putData("risk_level", riskLevel);
    }
  }

  /**
   * 使用redis缓存计算结果
   * @param openId
   * @param sessionKey
   * @return
   */
  @GetMapping("/risk/overall")
  public Result getOverallSituation(@RequestParam("openid") String openId,
                                    @RequestParam("session_key") String sessionKey,
                                    @RequestParam("province") String province,
                                    @RequestParam("city") String city) {
    Result verifiedResult = verifySession(openId, sessionKey);
    if (verifiedResult.getStatusCode() != 0) {
      return verifiedResult;
    }
    Integer lowLevelNumber = redisUtil.getOverallRiskLevel(province, city, 1);
    Integer mediumLevelNumber = redisUtil.getOverallRiskLevel(province, city, 2);
    Integer highLevelNumber = redisUtil.getOverallRiskLevel(province, city, 3);
    return new Result()
            .ok()
            .putData("low_level_number", lowLevelNumber)
            .putData("medium_level_number", mediumLevelNumber)
            .putData("high_level_number", highLevelNumber);
  }
}
