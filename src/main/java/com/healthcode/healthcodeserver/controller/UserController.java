package com.healthcode.healthcodeserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.healthcode.healthcodeserver.common.Result;
import com.healthcode.healthcodeserver.service.UserService;
import com.healthcode.healthcodeserver.util.WxUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wx/user")
public class UserController {
  @Autowired
  UserService userService;
  @Autowired
  WxUtil wxUtil;


  @GetMapping("/{appid}/login")
  public Result code2Session(@RequestParam("code") String code, @PathVariable String appid) {
    String data = wxUtil.code2Session(code);
    JSONObject jsonObject = JSONObject.parseObject(data);
    String sessionKey = jsonObject.getString("session_key");
    String openId = jsonObject.getString("openid");
    Result result = new Result();
    jsonObject.forEach(result::putData);
    if (sessionKey == null || openId == null) {
      return result.error();
    } else {
      return result.ok();
    }
  }
}
