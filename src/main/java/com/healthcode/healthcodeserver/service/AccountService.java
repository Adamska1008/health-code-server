package com.healthcode.healthcodeserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.healthcode.healthcodeserver.entity.Account;

public interface AccountService extends IService<Account> {
  //登陆验证
  public boolean accountIsValid(String userName,String userPassword,int category);
}
