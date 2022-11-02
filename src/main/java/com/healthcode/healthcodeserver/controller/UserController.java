package com.healthcode.healthcodeserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.healthcode.healthcodeserver.common.Result;
import com.healthcode.healthcodeserver.entity.NucleicAcidTestInfo;
import com.healthcode.healthcodeserver.entity.User;
import com.healthcode.healthcodeserver.entity.VaccineInoculationInfo;
import com.healthcode.healthcodeserver.service.CovidTestInstitutionService;
import com.healthcode.healthcodeserver.service.NucleicAcidTestInfoService;
import com.healthcode.healthcodeserver.service.UserService;
import com.healthcode.healthcodeserver.service.VaccineInoculationInfoService;
import com.healthcode.healthcodeserver.util.WxUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
  Map<String, String> openIdToSessionKey = new HashMap<>();

  @GetMapping("/{appid}/login")
  public Result code2Session(@RequestParam("code") String code,
                             @PathVariable String appid) {
    String data = wxUtil.code2Session(code);
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

  @GetMapping("/{appid}/index_profile")
  public Result getMainPageInfo(@RequestParam("openid") String openId,
                                @RequestParam("session_key") String sessionKey,
                                @PathVariable("appid") String appid) {
    if (!openIdToSessionKey.get(openId).equals(sessionKey)) {
      log.warn("receive openid "+openId+" and session_key "+sessionKey+", which do not correspond.");
      return new Result().error(3);
    }
    User user = userService.getUserInfoByOpenId(openId);
    if (user == null) {
      log.warn("no user with such openid");
      return new Result().error(4);
    }

    NucleicAcidTestInfo latestInfo = nucleicAcidTestInfoService.getLatestTestInfoByPersonId(user.getPersonId());
    String testInstitutionName = covidTestInstitutionService.getNameById(latestInfo.getTestInstitutionId());
    List<VaccineInoculationInfo> vaccineInoculationInfoList =
            vaccineInoculationInfoService.getVaccineInoculationInfoListByPersonId(user.getPersonId());
    log.info("return info of user with openid "+openId);
    return new Result().ok()
            .putData("name", user.getName())
            .putData("health_code_color", user.getHealthCodeColor())
            .putData("latest_test_result", latestInfo.getTestResult())
            .putData("latest_test_time", latestInfo.getTestTime())
            .putData("latest_test_institution", testInstitutionName)
            .putData("vaccine_inoculation_info", vaccineInoculationInfoList);
  }
}
