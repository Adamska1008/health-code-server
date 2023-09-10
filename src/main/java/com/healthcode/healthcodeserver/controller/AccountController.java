package com.healthcode.healthcodeserver.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthcode.healthcodeserver.aspect.pointCutAnnotation.FullLog;
import com.healthcode.healthcodeserver.common.Result;
import com.healthcode.healthcodeserver.entity.*;
import com.healthcode.healthcodeserver.service.*;
import com.healthcode.healthcodeserver.util.RedisUtil;
import com.healthcode.healthcodeserver.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@CrossOrigin
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
  @Autowired
  NucleicAcidTestInfoService nucleicAcidTestInfoService;
  @Autowired
  CovidTestInstitutionService covidTestInstitutionService;
  @Autowired
  VaccineInoculationInfoService vaccineInoculationInfoService;
  @Autowired
  CollectionPointService collectionPointService;
  @Autowired
  VenueCodeApplicationService venueCodeApplicationService;
  @Autowired
  RegionalRiskProfileService regionalRiskProfileService;
  @Autowired
  RedisUtil redisUtil;

  @Autowired
  UserRelationService userRelationService;

  /**
   * 管理员登陆获取验证token
   * @param username 用户名
   * @param password 密码
   * @return token值
   */
  @FullLog
  @GetMapping("/login")
  public Result adminLogIn(@RequestParam("username") String username,
                           @RequestParam("password") String password){
    QueryWrapper<Account> wrapper = new QueryWrapper<>();
    wrapper.eq("username", username);
    Account account = accountService.getOne(wrapper);
    if (account == null) {
      return new Result().error(201);
    }
    if (!account.getPassword().equals(password)) {
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
  @FullLog
  @GetMapping("/get_tester_apply")
  public Result getTesterApplicationInfo(@RequestParam("token") String token,
                                         @RequestParam("page") Integer page,
                                         @RequestParam("size") Integer size) {
    if (tokenUtil.verify(token)) {
      // 只统计未处理的数据条数
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
      return new Result()
              .error(2);
    }
  }

  /**
   * 前端向后端返回处理完的检测人员申请
   * @Param request 请求
   * @return Result
   */
  @FullLog
  @PostMapping("/post_tester_apply")
  public Result postTesterApplicationInfo(@RequestBody JSONObject request) {
    String token = request.getString("token");
    if (tokenUtil.verify(token)) {
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
      return new Result()
              .error(2)
              .message("unknown token");
    }
  }

  /**
   * 获取异地报备信息
   * @param token 用户通信凭证
   * @param page 当前页数，从 1 开始
   * @param size 当前页的大小
   * @return Result内容见文档
   */
  @FullLog
  @GetMapping("/remote_report")
  public Result getRemoteReport(@RequestParam("token") String token,
                                @RequestParam("page") Integer page,
                                @RequestParam("size") Integer size) {
    if (!tokenUtil.verify(token)) {
      return new Result()
              .error(2)
              .message("unknown token");
    }
    QueryWrapper<RemoteReporting> wrapper = new QueryWrapper<>();
    wrapper.lt("is_checked", 1);
    long tot = remoteReportingService.count(wrapper);

    List<RemoteReporting> remoteReportings;
    if (page == null || size == null) {
      return new Result().error(0).message("not given page or size");
    } else {
      remoteReportings = remoteReportingService.listByPage(page, size);
    }
    return new Result()
            .ok()
            .putData("total", tot)
            .putData("reporting_list", remoteReportings);
  }

  /**
   * 提交异地报备的修改
   * @param request 请求内容
   * @return Result内容见文档
   */
  @FullLog
  @PostMapping("remote_report")
  public Result postRemoteReport(@RequestBody JSONObject request) {
    String token = request.getString("token");
    if (!tokenUtil.verify(token)) {
      return new Result()
              .error(2)
              .message("unknown token");
    }
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
   * 获取异常信息申诉的列表
   * @param token 用户通信凭证
   * @return Result内容见文档
   */
  @FullLog
  @GetMapping("/abnormal")
  public Result getAbnormalInfo(@RequestParam("token") String token,
                                @RequestParam("page") Integer page,
                                @RequestParam("size") Integer size) {
    if (!tokenUtil.verify(token)) {
      return new Result()
              .error(2);
    }
    QueryWrapper<AbnormalInfo> wrapper = new QueryWrapper<>();
    wrapper.lt("is_investigated", 1);
    long tot = abnormalInfoService.count(wrapper);

    List<AbnormalInfo> abnormalInfos;
    abnormalInfos = abnormalInfoService.listByPage(page, size);
    return new Result()
            .ok()
            .putData("total", tot)
            .putData("abnormal_list", abnormalInfos);
  }

  /**
   * 提交异常信息修改
   * @param request 请求内容
   * @return Result 内容见文档
   */
  @FullLog
  @PostMapping("/abnormal")
  public Result postAbnormalInfo(@RequestBody JSONObject request) {
    String token = request.getString("token");
    if (!tokenUtil.verify(token)) {
      return new Result()
              .error(2);
    }
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
   * 获取家属健康码绑定
   * @param token 通信凭证
   * @return Result内容见文档
   */
  @FullLog
  @GetMapping("/family_binding")
  public Result getFamilyBinding(@RequestParam("token") String token,
                                 @RequestParam("page") Integer page,
                                 @RequestParam("size") Integer size) {
    if (!tokenUtil.verify(token)) {
      return new Result()
            .error(2);
    }
    QueryWrapper<FamilyBingApplication> wrapper = new QueryWrapper<>();
    wrapper.lt("is_processed", 1);
    long tot = familyBingApplicationService.count(wrapper);

    List<FamilyBingApplication> applications;
    applications = familyBingApplicationService.listByPage(page, size);
    return new Result()
            .ok()
            .putData("total", tot)
            .putData("application_list", applications);
  }


  /**
   * 家属健康码绑定
   * @param request 请求内容
   * @return Result 内容见文档
   */
  @FullLog
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
    if (request.getInteger("is_succeed") == 1) {
      QueryWrapper<FamilyBingApplication> queryWrapper = new QueryWrapper<>();
      queryWrapper.eq("application_id",applicationId);
      FamilyBingApplication familyBingApplication
              = familyBingApplicationService.getOne(queryWrapper);
      UserRelation userRelation = new UserRelation(
              familyBingApplication.getApplicantPersonId(),
              familyBingApplication.getRelativePersonId(),
              familyBingApplication.getRelationType().shortValue());
      userRelationService.save(userRelation);
    }
    return new Result().ok();
  }

  /**
   * 获取采集点信息
   * @param token
   * @param page
   * @param size
   * @return
   */
  @FullLog
  @GetMapping("/collection_point")
  public Result getCollectionPoint(@RequestParam("token") String token,
                                   @RequestParam("page") Integer page,
                                   @RequestParam("size") Integer size) {
    if (!tokenUtil.verify(token)) {
      return new Result()
              .error(2);
    }
    long tot = collectionPointService.count();
    List<CollectionPoint> points = collectionPointService.getByPage(page, size);
    return new Result()
            .ok()
            .putData("total", tot)
            .putData("point_list", points);
  }

  /**
   * 增加采集点
   * @param request 请求内容
   * @return Result 内容见文档
   */
  @FullLog
  @PostMapping("/collection_point/add")
  public Result addCollectionPoint(@RequestBody JSONObject request) {
    String token = request.getString("token");
    if (!tokenUtil.verify(token)) {
      return new Result()
              .error(2);
    }
    CollectionPoint point = new CollectionPoint(
            null,
            request.getString("collection_point_position"),
            request.getString("collection_point_location"),
            request.getString("collection_point_institution"),
            request.getString("collection_point_principal"),
            request.getString("collection_point_contact_phone")
    );
    collectionPointService.save(point);
    return new Result().ok();
  }

  /**
   * 修改采集点信息
   * @param request 请求内容
   * @return Result 内容见文档
   */
  @FullLog
  @PostMapping("/collection_point/update")
  public Result updateCollectionPoint(@RequestBody JSONObject request) {
    String token = request.getString("token");
    if (!tokenUtil.verify(token)) {
      return new Result()
              .error(2);
    }
    String pointId = request.getString("collection_point_id");
    UpdateWrapper<CollectionPoint> wrapper = new UpdateWrapper<>();
    wrapper.eq("collection_point_id", pointId);
    List<String> selections = Arrays.asList(
            "collection_point_position", "collection_point_location",
            "collection_point_institution", "collection_point_principal",
            "collection_point_contact_phone");
    for (String selection : selections) {
      if (request.containsKey(selection)) {
        wrapper.set(selection, request.getString(selection));
      }
    }
    collectionPointService.update(wrapper);
    return new Result().ok();
  }

  /**
   * 获取用户基本信息
   * @param token 通信凭证
   * @param personId 身份证号
   * @return Result内容见文档
   */
  @FullLog
  @GetMapping("/user/profile")
  public Result getUserProfile(@RequestParam("token") String token,
                               @RequestParam("person_id") String personId) {

    if (!tokenUtil.verify(token)) {
      return new Result()
              .error(2);
    }
    User user = userService.getByPersonId(personId);
    if (user == null) {
      return new Result()
              .error(null)
              .message("invalid person id");
    }
    return new Result()
            .ok()
            .putData("person_name", user.getName())
            .putData("person_id", personId)
            .putData("gender", user.getGender())
            .putData("phone_number", user.getPhoneNumber())
            .putData("health_code_color", user.getHealthCodeColor());
  }

  /**
   * 获取指定身份证号码用户的行程信息
   * @param token 用户通信凭证
   * @param personId 身份证号
   * @return Result内容参考文档
   */
  @FullLog
  @GetMapping("/user/itinerary")
  public Result getItinerary(@RequestParam("token") String token,
                             @RequestParam("person_id") String personId) throws JsonProcessingException {
    if (!tokenUtil.verify(token)) {
      return new Result()
              .error(2)
              .message("unknown token");
    }
    Result result = new Result().ok();
    User user = userService.getByPersonId(personId);
    if (user == null) {
      return new Result()
              .error(null)
              .message("invalid person id");
    }
    QueryWrapper<ItineraryInfo> wrapper = new QueryWrapper<>();
    JSONArray jsonArray = new JSONArray();
    wrapper.eq("person_id", personId);
    List<ItineraryInfo> itineraryInfos = itineraryInfoService.list(wrapper);
    itineraryInfos.sort(Comparator.comparing(ItineraryInfo::getRecordTime));
    ObjectMapper mapper = new ObjectMapper();
    for (var itineraryInfo : itineraryInfos) {
      JSONObject object = new JSONObject();
      object.put("person_name", user.getName());
      String venueName = venueCodeInfoService.getVenueNameById(itineraryInfo.getVenueId());
      object.put("place_name", venueName);
      JSONObject stringParse = JSON.parseObject(mapper.writeValueAsString(itineraryInfo));
      object.put("record_time", stringParse.getString("record_time"));
      jsonArray.add(object);
    }
    return result.putData("itinerary_info", jsonArray);
  }

  /**
   * 获取用户核酸检测信息
   * @param token 通信凭证
   * @param personId 身份证号
   * @return Result内容见文档
   */
  @FullLog
  @GetMapping("/user/nucleic_test")
  public Result getNucleicTestInfo(@RequestParam("token") String token,
                                   @RequestParam("person_id") String personId) throws JsonProcessingException {
    if (!tokenUtil.verify(token)) {
      return new Result()
              .error(2)
              .message("unknown token");
    }
    User user = userService.getByPersonId(personId);
    if (user == null) {
      return new Result()
              .error(null)
              .message("invalid person id");
    }
    List<NucleicAcidTestInfo> nucleicAcidTestInfos =
            nucleicAcidTestInfoService.getNucleicAcidTestInfoListByPersonId(user.getPersonId());
    JSONArray infoList = new JSONArray();
    ObjectMapper mapper = new ObjectMapper();
    for (var info : nucleicAcidTestInfos) {
      var jsonStr = JSON.parseObject(mapper.writeValueAsString(info));
      JSONObject jsonInfo = new JSONObject();
      jsonInfo.put("person_id", info.getPersonId());
      jsonInfo.put("test_result", info.getTestResult());
      jsonInfo.put("test_time", jsonStr.getString("test_time"));
      jsonInfo.put("transfer_code", info.getTransferCode());
      CovidTestInstitution covidTestInstitution =
              covidTestInstitutionService.getById(info.getTestInstitutionId());
      jsonInfo.put("institution_name", covidTestInstitution.getName());
      infoList.add(jsonInfo);
    }
    return new Result()
            .ok()
            .putData("info_list", infoList);
  }

  /**
   * 获取用户疫苗接种信息
   * @param token 通信凭证
   * @param personId 身份证号
   * @return Result内容见文档
   */
  @FullLog
  @GetMapping("/user/vaccine_inocu")
  public Result getVaccineInocuInfo(@RequestParam("token") String token,
                                    @RequestParam("person_id") String personId) {
    if (!tokenUtil.verify(token)) {
      return new Result()
              .error(2)
              .message("unknown token");
    }
    User user = userService.getByPersonId(personId);
    if (user == null) {
      return new Result()
              .error(null)
              .message("invalid person id");
    }
    List<VaccineInoculationInfo> vaccineInoculationInfos =
            vaccineInoculationInfoService.getInfoListByPersonId(user.getPersonId());
    return new Result()
            .ok()
            .putData("info_list", vaccineInoculationInfos);
  }

  /**
   * 获取场所码信息
   * @param token
   * @param page
   * @param size
   * @return
   */
  @FullLog
  @GetMapping("/venue_code/application")
  public Result getVenueCodeApplication(@RequestParam("token") String token,
                                        @RequestParam("page") Integer page,
                                        @RequestParam("size") Integer size) {
    if (!tokenUtil.verify(token)) {
      return new Result()
              .error(2)
              .message("unknown token");
    }
    QueryWrapper<VenueCodeApplication> wrapper = new QueryWrapper<>();
    wrapper.lt("is_solved", 1);
    long tot = venueCodeApplicationService.count(wrapper);
    List<VenueCodeApplication> venueCodeApplications
            = venueCodeApplicationService.getByPage(page, size);
    return new Result()
            .ok()
            .putData("total", tot)
            .putData("application_list", venueCodeApplications);
  }

  /**
   * 返回场所码信息的审批结果
   * @param request
   * @return
   */
  @FullLog
  @PostMapping("/venue_code/application")
  public Result postVenueCodeApplication(@RequestBody JSONObject request) {
    String token = request.getString("token");
    if (!tokenUtil.verify(token)) {
      return new Result()
              .error(2)
              .message("unknown token");
    }
    String applicationId = request.getString("application_id");
    Integer isPassed = request.getInteger("is_passed");
    String resultInfo = request.getString("result_info");
    VenueCodeApplication application = venueCodeApplicationService.getById(applicationId);
    UpdateWrapper<VenueCodeApplication> wrapper = new UpdateWrapper<>();
    if (isPassed == 1) {
      VenueCodeInfo info = new VenueCodeInfo(
              null,
              application.getType(),
              application.getPlaceName(),
              application.getPosition(),
              application.getLocation()
      );
      venueCodeInfoService.save(info);
      wrapper.set("venue_id", info.getId());
    }
    wrapper.eq("application_id", applicationId);
    wrapper.set("is_solved", 1);
    wrapper.set("is_passed", isPassed);
    wrapper.set("result_info", resultInfo);
    venueCodeApplicationService.update(wrapper);
    return new Result()
            .ok();
  }


  /**
   * 获取某个地区的红黄码数量
   * @param token
   * @param province
   * @param city
   * @param district
   * @return
   */
  @FullLog
  @GetMapping("/risk/code_number")
  public Result getCodeNumber(@RequestParam("token") String token,
                              @RequestParam("province") String province,
                              @RequestParam("city") String city,
                              @RequestParam("district") String district) {
    if (!tokenUtil.verify(token)) {
      return new Result()
              .error(2)
              .message("unknown token");
    }
    String position = province + ":" + city + ":" + district;
    Long green = regionalRiskProfileService.getCodeNumber(position, 0);
    Long yellow = regionalRiskProfileService.getCodeNumber(position, 1);
    Long red = regionalRiskProfileService.getCodeNumber(position, 2);
    return new Result()
            .ok()
            .putData("green", green)
            .putData("yellow", yellow)
            .putData("red", red);
  }

  /**
   *
   * @param token
   * @param province
   * @param city
   * @param district
   * @return
   */
  @FullLog
  @GetMapping("/risk/specific")
  public Result getSpecificAreaSituation(@RequestParam("token") String token,
                                         @RequestParam("province") String province,
                                         @RequestParam("city") String city,
                                         @RequestParam("district") String district) {
    if (!tokenUtil.verify(token)) {
      return new Result()
              .error(2)
              .message("unknown token");
    }
    int riskLevel = regionalRiskProfileService.getRiskLevel(province, city, district);
    if (riskLevel == -1) {
      riskLevel = 0;
    }
    int positiveNumber = regionalRiskProfileService.getPositiveNumber(province, city, district);
    return new Result()
            .ok()
            .putData("risk_level", riskLevel)
            .putData("positive_number", positiveNumber);
  }

  /**
   *
   * @param token
   * @param province
   * @param city
   * @return
   */
  @FullLog
  @GetMapping("/risk/overall")
  public Result getOverallSituation(@RequestParam("token") String token,
                                    @RequestParam(value = "province", required = false)
                                    String province,
                                    @RequestParam(value = "city", required = false)
                                    String city) {
    if (!tokenUtil.verify(token)) {
      return new Result()
              .error(2)
              .message("unknown token");
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

  /**
   *
   * @param token
   * @param province
   * @param city
   * @return
   */
  @FullLog
  @GetMapping("/risk/sub_area")
  public Result getSubArea(@RequestParam("token") String token,
                           @RequestParam(value = "province", required = false)
                           String province,
                           @RequestParam(value = "city", required = false)
                           String city) {
    if (!tokenUtil.verify(token)) {
      return new Result()
              .error(2)
              .message("unknown token");
    }
    List<String> subAreas = regionalRiskProfileService.getSubArea(province, city);
    return new Result()
            .ok()
            .putData("sub_areas", subAreas);
  }

  /**
   *
   * @param token
   * @param province
   * @return
   */
  @FullLog
  @GetMapping("/risk/province")
  public Result getProvinceSituation(@RequestParam("token") String token,
                                     @RequestParam("province") String province) {
    if (!tokenUtil.verify(token)) {
      return new Result()
              .error(2)
              .message("unknown token");
    }
    List<String> cities = regionalRiskProfileService.getSubArea(province, null);
    JSONArray jsonArray = new JSONArray();
    for (var city : cities) {
      JSONObject object = new JSONObject();
      object.put("city", city);
      Integer lowLevelNumber = redisUtil.getOverallRiskLevel(province, city, 1);
      Integer mediumLevelNumber = redisUtil.getOverallRiskLevel(province, city, 2);
      Integer highLevelNumber = redisUtil.getOverallRiskLevel(province, city, 3);
      object.put("low_level_number", lowLevelNumber);
      object.put("medium_level_number", mediumLevelNumber);
      object.put("high_level_number", highLevelNumber);
      jsonArray.add(object);
    }
    return new Result()
            .ok()
            .putData("cities", jsonArray);
  }

  /**
   *
   * @param token
   * @param province
   * @param city
   * @param district
   * @return
   */
  @FullLog
  @GetMapping("/test/general")
  public Result getGeneralTestInfo(@RequestParam(value = "token", required = false)
                                   String token,
                                   @RequestParam(value = "province", required = false)
                                   String province,
                                   @RequestParam(value = "city", required = false)
                                   String city,
                                   @RequestParam(value = "district",required = false)
                                   String district) {
    if (!tokenUtil.verify(token)) {
      return new Result()
              .error(2)
              .message("unknown token");
    }
    if (province == null) {
      return new Result()
              .error(null)
              .message("missing ${province} param");
    }
    Long positiveNumber = userService.getDistrictPositive(province, city, district, 1);
    Long negativeNumber = userService.getDistrictPositive(province, city, district, 0);
    return new Result()
            .ok()
            .putData("positive", positiveNumber)
            .putData("negative", negativeNumber);
  }
}
