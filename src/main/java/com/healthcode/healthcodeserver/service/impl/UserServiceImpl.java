package com.healthcode.healthcodeserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.healthcode.healthcodeserver.dao.UserDao;
import com.healthcode.healthcodeserver.entity.User;
import com.healthcode.healthcodeserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao,User> implements UserService {
  @Autowired
  private UserDao userDao;

  @Override
  public User getUserInfoByPersonId(String personId) {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("person_id",personId);
    return userDao.selectOne(queryWrapper);
  }

  public User getUserInfoByOpenId(String openId) {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("wx_openid",openId);
    return userDao.selectOne(queryWrapper);
  }
}
