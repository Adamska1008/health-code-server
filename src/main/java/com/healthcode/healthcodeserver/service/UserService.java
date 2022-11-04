package com.healthcode.healthcodeserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.healthcode.healthcodeserver.entity.User;
import org.springframework.stereotype.Service;

public interface UserService extends IService<User> {
  User getUserInfoByPersonId(String personId);
  User getUserInfoByOpenId(String openId);

  int insertUserInfo(String personId,String personName,String phoneNumber,String wxOpenid,String gender);
}
