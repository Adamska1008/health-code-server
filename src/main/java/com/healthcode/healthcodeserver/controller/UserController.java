package com.healthcode.healthcodeserver.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson2.util.JSONObject1O;
import com.healthcode.healthcodeserver.common.Result;
import com.healthcode.healthcodeserver.entity.*;
import com.healthcode.healthcodeserver.service.*;
import com.healthcode.healthcodeserver.util.WxUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
      log.warn("receive openid "+openId+" and session_key "+sessionKey+", which do not correspond.");
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
    User user = userService.getUserInfoByOpenId(openId);
      if (user == null) {
      log.warn("no user with such openid");
      return new Result().error(4);
    }
    NucleicAcidTestInfo latestInfo = nucleicAcidTestInfoService.getLatestTestInfoByPersonId(user.getPersonId());
    String testInstitutionName = covidTestInstitutionService.getNameById(latestInfo.getTestInstitutionId());

    JSONObject latestTest = new JSONObject();
    latestTest.put("result", latestInfo.getTestResult());
    latestTest.put("test_time", latestInfo.getTestTime());
    latestTest.put("institution_name", testInstitutionName);

    List<VaccineInoculationInfo> vaccineInoculationInfoList =
            vaccineInoculationInfoService.getInfoListByPersonId(user.getPersonId());

    log.info("return info of user with openid "+openId);
    return new Result().ok()
            .putData("name", user.getName())
            .putData("health_code_color", user.getHealthCodeColor())
            .putData("latest_test", latestTest)
            .putData("vaccine_inoculation_info", vaccineInoculationInfoList);
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
    User user = userService.getUserInfoByOpenId(openId);
    if (user == null) {
      log.warn("no user with such openid");
      return new Result()
              .error(4);
    }
    List<NucleicAcidTestInfo> nucleicAcidTestInfos =
            nucleicAcidTestInfoService.getNucleicAcidTestInfoListByPersonId(user.getPersonId());
    return new Result().ok()
            .putData("test_info", nucleicAcidTestInfos);
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
    User user = userService.getUserInfoByOpenId(openId);
    if (user == null) {
      log.warn("no user with such openid");
      return new Result()
              .error(103);
    }
    List<VaccineInoculationInfo> vaccineInoculationInfos =
            vaccineInoculationInfoService.getInfoListByPersonId(user.getPersonId());
    return new Result()
            .ok()
            .putData("test_info", vaccineInoculationInfos);
  }

  /**
   * 申请场所码
   * @param response http请求体
   * @return 申请提交成功的result
   */
  @PostMapping("/venue_code")
  public Result applyVenueCode(@RequestBody JSONObject response) {
    String openId = response.getString("openid");
    String sessionKey = response.getString("session_key");
    Result verifiedResult = verifySession(openId, sessionKey);
    if (verifiedResult.getStatusCode() != 0) {
      return verifiedResult;
    }
    VenueCodeApplication application = new VenueCodeApplication(
            null,
            response.getString("applicant_name"),
            response.getString("applicant_person_id"),
            response.getString("location"),
            response.getString("type"),
            response.getString("place_name"),
            0,
            0,
            null
    );
    venueCodeApplicationService.save(application);
    return new Result()
            .ok()
            .putData("application_id", application.getId());
  }


  /**
   * 获取场所码申请的状态
   * @param openId 用户openid
   * @param sessionKey 用户session_key
   * @return 详情见文档
   */
  @GetMapping("venue_code")
  public Result checkVenueCode(@RequestParam("openid") String openId,
                               @RequestParam("session_key") String sessionKey,
                               @RequestParam("applicant_name") String application_id) {
    Result verifiedResult = verifySession(openId, sessionKey);
    if (verifiedResult.getStatusCode() != 0) {
      return verifiedResult;
    }
    VenueCodeApplication application = venueCodeApplicationService.getById(application_id);
    return new Result()
            .ok()
            .putData("processed", application.getIsSolved())
            .putData("venue_code_id", application.getId())
            .putData("result", application.getResult())
            .putData("result_info", application.getResultInfo());
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
            request.getString("relative_name"),
            request.getString("relative_person_id"),
            request.getString("additional_info"),
            request.getInteger("relation_type"),
            0, 0, null
    );
    familyBingApplicationService.save(application);
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
    return new Result()
            .ok()
            .putData("processed", application.getIsProcessed())
            .putData("succeed", application.getIsSucceed());
  }

  /**
   * 查看家属健康码信息，
   * @param openId 用户小程序openid
   * @param sessionKey 会话密钥
   * @param person_id 被查询用户的身份证号
   * @return 数据类似于个人主界面信息，详情见文档
   */
  @GetMapping("/family_profile")
  public Result getFamilyPageInfo(@RequestParam("openid") String openId,
                                  @RequestParam("session_key") String sessionKey,
                                  @RequestParam("person_id") String person_id) {
    Result verifiedResult = verifySession(openId, sessionKey);
    if (verifiedResult.getStatusCode() != 0) {
      return verifiedResult;
    }
    User user = userService.getUserInfoByOpenId(openId);
    UserRelation relation = userRelationService.getRelationByTwoIds(user.getPersonId(), person_id);
    if (relation == null) {
      return new Result().error(null).message("Unregistered relation.");
    }
    user = userService.getUserInfoByPersonId(person_id);
    NucleicAcidTestInfo latestInfo = nucleicAcidTestInfoService.getLatestTestInfoByPersonId(person_id);
    String testInstitutionName = covidTestInstitutionService.getNameById(latestInfo.getTestInstitutionId());

    JSONObject latestTest = new JSONObject();
    latestTest.put("result", latestInfo.getTestResult());
    latestTest.put("test_time", latestInfo.getTestTime());
    latestTest.put("institution_name", testInstitutionName);

    List<VaccineInoculationInfo> vaccineInoculationInfoList =
            vaccineInoculationInfoService.getInfoListByPersonId(person_id);

    log.info("return info of user with openid "+openId);
    return new Result().ok()
            .putData("name", user.getName())
            .putData("health_code_color", user.getHealthCodeColor())
            .putData("latest_test", latestTest)
            .putData("vaccine_inoculation_info", vaccineInoculationInfoList);
  }
}
