package com.healthcode.healthcodeserver.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.healthcode.healthcodeserver.common.Result;
import com.healthcode.healthcodeserver.entity.IdentityApplication;
import com.healthcode.healthcodeserver.entity.NucleicAcidTestInfo;
import com.healthcode.healthcodeserver.entity.TransferCodeInfo;
import com.healthcode.healthcodeserver.service.*;
import com.healthcode.healthcodeserver.util.WxUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Slf4j
@RestController
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

  @Autowired
  TransferCodeInfoService transferCodeInfoService;

  @Autowired
  NucleicAcidTestInfoService nucleicAcidTestInfoService;

  Map<String, String> openIdToSessionKey = new HashMap<>();

  /**
   * 鉴定session是否合法
   * @param openId 微信开发的openid
   * @param sessionKey 会话密钥
   * @return 是否合法的Result值
   */
  private Result verifySession(String openId,
                               String sessionKey) {
    if (!openIdToSessionKey.containsKey(openId)) {
      log.warn("do not have given openid: "+ openId);
      return new Result()
              .error(3);
    }
    if (!openIdToSessionKey.get(openId).equals(sessionKey)) {
      log.warn("receive openid "+openId+" and session_key "+sessionKey+", which do not correspond.");
      return new Result()
              .error(101);
    }
    log.info("verify session with openid " +openId+" and session_key "+sessionKey);
    return new Result()
            .ok();
  }

  /**
   * 核酸检测人员登陆
   * @param code 小程序给定的code值
   * @param appid 微信小程序appid
   * @return 若该人员已通过申请，则直接登陆
   */
  @GetMapping("/{appid}/login")
  public Result code2Session(@RequestParam("code") String code,
                             @PathVariable String appid) {
    String data = wxUtil.code2Session(code, 1);
    JSONObject jsonObject = JSONObject.parseObject(data);
    log.info("request to build session with code "+ code);
    String sessionKey = jsonObject.getString("session_key");
    String openId = jsonObject.getString("openid");
    Result result = new Result();
    jsonObject.forEach(result::putData);
    result.putData("isTester", testerService.isTester(openId)?1:0);
    if (sessionKey == null || openId == null) {
      return result.error(2);
    } else {
      openIdToSessionKey.put(openId, sessionKey);
      log.info("result " + result);
      return result.ok();
    }
  }

  /**
   * 登陆小程序人员不是核酸检测人员时需要提交表单以申请权限，这里处理申请信息
   * @param openId
   * @param sessionKey
   * @param name
   * @param idNumber
   * @param phoneNumber
   * @param appId
   * @return 返回的是申请的提交结果，result的data封装了status属性，为1表示申请成功，为0表示申请失败
   */
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
              null, openId, name, idNumber, phoneNumber, null,
              0, 0, 0, null);
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

  /**
   * 从参数体中获得转运码相关的openid、number等信息，其中person中存储了核酸检测表相关信息
   * 这个函数根据这些信息分别向核酸检测表和核酸转运码表插入数据
   * @param request
   * @return result的data中封装了submitResult属性，为1表示插入成功，为2表示转运码使用过已失效
   */
  @PostMapping("/{appid}/testInfo")
  public Result getTestInfo(@RequestBody JSONObject request){
    //todo 登陆检查
    Result result = new Result();
    String openId = request.getString("openid");
    String number = request.getString("number");
    JSONArray personList = request.getJSONArray("person");
    String time = request.getString("time");
    String transferCode = request.getString("transferCode");
    //向转运码表插入一条数据
    TransferCodeInfo transferCodeInfo = new TransferCodeInfo(transferCode,openId,Timestamp.valueOf(time),(Integer.parseInt(number)), (short) 0);
    //transferCode已经使用过，则无效，直接返回错误信息
    if (!transferCodeInfoService.save(transferCodeInfo)){
      result.putData("submitResult",2);
      return result.ok();
    }

    //向核酸检测表插入数据,当前不考虑person的phone和name属性
    int size = personList.size();
    for (int i=0;i<size;i++){
      JSONObject jsonObject = personList.getJSONObject(i);
      NucleicAcidTestInfo nucleicAcidTestInfo = new NucleicAcidTestInfo();
      nucleicAcidTestInfo.setTestTime(Timestamp.valueOf(jsonObject.getString("time")));
      nucleicAcidTestInfo.setTransferCode(transferCode);
      nucleicAcidTestInfo.setPersonId(jsonObject.getString("idNumber"));
      if(!nucleicAcidTestInfoService.save(nucleicAcidTestInfo)){
        result.putData("submitResult",1);
        return result;
      }
    }
    result.putData("submitResult",1);

    return result.ok();
  }

  /**
   * 返回没有进行转运的转运码信息
   * @param openId 用户openid
   * @return 在Result的data中封装未转运信息
   */
  @GetMapping("/{appid}/notTransferredInfo")
  public Result getNotTransferredInfo(@RequestParam("openid") String openId){
    Result result = new Result();
    List<TransferCodeInfo> notTransferredList = transferCodeInfoService.getNotTransferredByOpenId(openId);
    String jsonString = JSON.toJSONString(notTransferredList);
    result.putData("transferList",jsonString);
    return result.ok();
  }

}
