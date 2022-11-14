package com.healthcode.healthcodeserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.healthcode.healthcodeserver.entity.Account;

public interface AccountService extends IService<Account> {
  /**
   * 登陆验证
   * @param userName 用户名
   * @param userPassword 密码
   * @param category 账户类型
   * @return 是否合法
   */
  boolean accountIsValid(String userName,String userPassword,int category);
}
