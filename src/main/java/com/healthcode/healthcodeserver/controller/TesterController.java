package com.healthcode.healthcodeserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.healthcode.healthcodeserver.common.Result;
import com.healthcode.healthcodeserver.entity.IdentityApplication;
import com.healthcode.healthcodeserver.entity.User;
import com.healthcode.healthcodeserver.service.IdentityApplicationService;
import com.healthcode.healthcodeserver.service.TesterService;
import com.healthcode.healthcodeserver.service.UserService;
import com.healthcode.healthcodeserver.util.WxUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/wx/tester")
public class TesterController {
  @Autowired
  UserService userService;
  @Autowired
  WxUtil wxUtil;
  @Autowired
  IdentityApplicationService identityApplicationService;
  @Autowired
  TesterService testerService;

  Map<String, String> openIdToSessionKey = new HashMap<>();

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

  @GetMapping("/{appid}/login")
  public Result code2Session(@RequestParam("code") String code,
                             @PathVariable String appid) {
//    System.out.println("logIn Started");
    String data = wxUtil.code2Session(code, 1);
    JSONObject jsonObject = JSONObject.parseObject(data);
    log.info("request to build session with code "+ code);
    String sessionKey = jsonObject.getString("session_key");
    String openId = jsonObject.getString("openid");
    Result result = new Result();
    jsonObject.forEach(result::putData);
//    System.out.println("ok here:"+openId);
    result.putData("isTester", testerService.isTester(openId)?1:0);
    if (sessionKey == null || openId == null) {
      return result.error(2);
    } else {
      openIdToSessionKey.put(openId, sessionKey);
      return result.ok();
    }
  }

  @GetMapping("/{appid}/apply")
  public Result getApplicationInfo(@RequestParam("openid") String openId,
                                   @RequestParam("sessionKey") String sessionKey,
                                   @RequestParam("name") String name,
                                   @RequestParam("idNumber") String idNumber,
                                   @RequestParam("phoneNumber") String phoneNumber,
                                   @PathVariable("appid") String appId){
    Result verifiedResult = verifySession(openId, sessionKey);
    if (verifiedResult.getStatusCode() != 0) {
      log.info("user with openid "+openId + " did not fetch session_key.");
      return verifiedResult;
    }
    Result result = new Result();
    if (identityApplicationService.hasApplicationRecord(idNumber)){
      result.putData("status",1);
    } else {//有申请记录
      IdentityApplication identityApplication = new IdentityApplication(
              null, openId, name, idNumber, phoneNumber, null, 0, 0, 0, null);
      log.info(name + " wants to apply for tester");
      identityApplicationService.save(identityApplication);
      result.putData("status",0);
    }
    return result.ok();
  }

  /**
   * 申请成为核酸检测人员接口
   * @param form 注册信息，包括姓名，身份证号，手机号
   * @return Result
   */
  @PostMapping("/{appid}/register")
  public Result register(@RequestParam("openid") String openId,
                         @RequestParam("session_key") String sessionKey,
                         @RequestBody JSONObject form,
                         @PathVariable String appid) {
    Result verifiedResult = verifySession(openId, sessionKey);
    if (verifiedResult.getStatusCode() != 0) {
      return verifiedResult;
    }
    IdentityApplication application = new IdentityApplication();
    application.setApplicantName(form.getString("name"));
    application.setApplicantPersonId(form.getString("person_id"));
    application.setApplicantPhone(form.getString("phone"));
    application.setType(0);
    identityApplicationService.save(application);
    return new Result().ok();
  }
}
