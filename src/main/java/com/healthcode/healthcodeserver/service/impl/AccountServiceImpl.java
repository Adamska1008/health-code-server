package com.healthcode.healthcodeserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.healthcode.healthcodeserver.dao.AccountDao;
import com.healthcode.healthcodeserver.entity.Account;
import com.healthcode.healthcodeserver.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl extends ServiceImpl<AccountDao, Account> implements AccountService {
  @Autowired
  AccountDao accountDao;

  @Override
  public boolean accountIsValid(String userName, String userPassword, int category) {
    QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("user_name",userName);
    queryWrapper.eq("user_password",userPassword);
    queryWrapper.eq("category",category);
    return !accountDao.selectList(queryWrapper).isEmpty();
  }
}