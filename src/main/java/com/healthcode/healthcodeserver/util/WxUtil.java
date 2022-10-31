package com.healthcode.healthcodeserver.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class WxUtil {
  @Autowired
  private RestTemplate restTemplate;
  @Value("${app.id}")
  private String appId;
  @Value("${app.secret}")
  private String appSecret;

  /**
   * 通过 wx.login 接口获得临时登录凭证 code 后传到开发者服务器调用此接口完成登录流程
   * @param code 临时登录凭证
   */
  public String code2Session(String code) {
    String url = "https://api.weixin.qq.com/sns/jscode2session";
    Map<String, String> map = new HashMap<>(){{
      put("appid", appId);
      put("secret", appSecret);
      put("js_code", code);
      put("grant_type", "authorization_code");
    }};
    ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class, map);
    return responseEntity.getBody();
  }
}
