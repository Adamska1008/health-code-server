package com.healthcode.healthcodeserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.healthcode.healthcodeserver.entity.Tester;

public interface TesterService extends IService<Tester> {
  public Boolean isTester(String openId);

}