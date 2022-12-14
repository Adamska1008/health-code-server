package com.healthcode.healthcodeserver.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthcode.healthcodeserver.common.Result;
import com.healthcode.healthcodeserver.entity.*;
import com.healthcode.healthcodeserver.service.*;
import com.healthcode.healthcodeserver.util.RedisUtil;
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

import java.util.Arrays;
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
   * ???????????????????????????token
   * @param username ?????????
   * @param password ??????
   * @return token???
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
   * ????????????????????????????????????????????????
   * @param token ??????????????????token
   * @return ????????????????????????
   */
  @GetMapping("/get_tester_apply")
  public Result getTesterApplicationInfo(@RequestParam("token") String token,
                                         @RequestParam("page") Integer page,
                                         @RequestParam("size") Integer size) {
    log.info("admin with token "+token+" acquire tester apply info");
    if (tokenUtil.verify(token)) {
      log.info("admin with token "+token+" successfully get application list.");
      // ??????????????????????????????
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
   * ???????????????????????????????????????????????????
   * @Param request ??????
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
   * ????????????????????????
   * @param token ??????????????????
   * @return Result???????????????
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
   * ???????????????????????????
   * @param request ????????????
   * @return Result???????????????
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
   * ?????????????????????????????????
   * @param token ??????????????????
   * @return Result???????????????
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
    abnormalInfos = abnormalInfoService.listByPage(page, size);
    return new Result()
            .ok()
            .putData("total", tot)
            .putData("abnormal_list", abnormalInfos);
  }

  /**
   * ????????????????????????
   * @param request ????????????
   * @return Result ???????????????
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
   * ???????????????????????????
   * @param token ????????????
   * @return Result???????????????
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
    applications = familyBingApplicationService.listByPage(page, size);
    return new Result()
            .ok()
            .putData("total", tot)
            .putData("application_list", applications);
  }


  /**
   * ?????????????????????
   * @param request ????????????
   * @return Result ???????????????
   */
  @PostMapping("/family_binding")
  public Result postFamilyBinding(@RequestBody JSONObject request) {
    String token = request.getString("token");
    if (!tokenUtil.verify(token)) {
      return new Result()
              .error(2);
    }
    log.info("Admin with token " + token + " post family binding.");
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
   * ?????????????????????
   * @param token
   * @param page
   * @param size
   * @return
   */
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
   * ???????????????
   * @param request ????????????
   * @return Result ???????????????
   */
  @PostMapping("/collection_point/add")
  public Result addCollectionPoint(@RequestBody JSONObject request) {
    String token = request.getString("token");
    if (!tokenUtil.verify(token)) {
      log.warn("invalid token " + token);
      return new Result()
              .error(2);
    }
    log.info("Admin with token " + token + " add collection point.");
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
   * ?????????????????????
   * @param request ????????????
   * @return Result ???????????????
   */
  @PostMapping("/collection_point/update")
  public Result updateCollectionPoint(@RequestBody JSONObject request) {
    String token = request.getString("token");
    if (!tokenUtil.verify(token)) {
      return new Result()
              .error(2);
    }
    log.info("Admin with token " + token + " update collection point.");
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
   * ????????????????????????
   * @param token ????????????
   * @param personId ????????????
   * @return Result???????????????
   */
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
   * ????????????????????????????????????????????????
   * @param token ??????????????????
   * @param personId ????????????
   * @return Result??????????????????
   */
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
   * ??????????????????????????????
   * @param token ????????????
   * @param personId ????????????
   * @return Result???????????????
   */
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
   * ??????????????????????????????
   * @param token ????????????
   * @param personId ????????????
   * @return Result???????????????
   */
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
   * ?????????????????????
   * @param token
   * @param page
   * @param size
   * @return
   */
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
   * ????????????????????????????????????
   * @param request
   * @return
   */
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
      wrapper.set("venue_id", info.getId());
      venueCodeInfoService.save(info);
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
   *
   * @param token
   * @param province
   * @param city
   * @param district
   * @return
   */
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
    Integer riskLevel = regionalRiskProfileService.getRiskLevel(province, city, district);
    Integer positiveNumber = regionalRiskProfileService.getPositiveNumber(province, city, district);
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
            .putData("sub_ares", subAreas);
  }

  /**
   *
   * @param token
   * @param province
   * @return
   */
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
