package com.healthcode.healthcodeserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.healthcode.healthcodeserver.entity.User;
import org.springframework.stereotype.Service;

public interface UserService extends IService<User> {
  User getUserInfoById(String id);
}
