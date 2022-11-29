package com.healthcode.healthcodeserver.util;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
@Data
public class WxUtil {
  @Autowired
  private RestTemplate restTemplate;
  @Value("${main_app.id}")
  private String mainAppId;
  @Value("${main_app.secret}")
  private String mainAppSecret;
  @Value("${test_app.id}")
  private String testAppId;
  @Value("${test_app.secret}")
  private String testAppSecret;

  /**
   * 通过 wx.login 接口获得临时登录凭证 code 后传到开发者服务器调用此接口完成登录流程
   * @param type 0 表示主程序 1表示核酸检测程序
   * @param code 临时登录凭证
   */
  public String code2Session(String code, Integer type) {
    String url =
"https://api.weixin.qq.com/sns/jscode2session?appid={appid}&secret={secret}&js_code={js_code}&grant_type=authorization_code";
    Map<String, Object> map = new HashMap<>(){{
      if (type == 0) {
        put("appid", mainAppId);
        put("secret", mainAppSecret);
      } else {
        put("appid", testAppId);
        put("secret", testAppSecret);
      }
      put("js_code", code);
    }};
    ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class, map);
    return responseEntity.getBody();
  }
}
