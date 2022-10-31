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
  UserService userService;
  @Override
  public User getUserInfoById(String id) {
    QueryWrapper queryWrapper=new QueryWrapper<>()
            .eq("person_id",id);
    return userService.getOne(queryWrapper);
  }
}
