package com.healthcode.healthcodeserver.util;

import com.healthcode.healthcodeserver.entity.Account;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
public class TokenUtil {
  private Map<String, String> tokenToAccountId;

  /**
   * 为用户生成唯一的token
   * @param account 管理员用户
   * @return 生成的token
   */
  public String gen(Account account) {
    String token = UUID.randomUUID().toString();
    tokenToAccountId.put(token, account.getAccountId());
    return token;
  }

  /**
   * 验证token是否合法
   * @return 布尔值，表示是否合法
   */
  public boolean verify(String token) {
    return tokenToAccountId.containsKey(token);
  }

  /**
   * 通过token获取account id
   * @return accountId
   */
  public String getAccountId(String token) {
    return tokenToAccountId.get(token);
  }
}
