package com.healthcode.healthcodeserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.healthcode.healthcodeserver.entity.Tester;

public interface TesterService extends IService<Tester> {
  /**
   * 查询是否存在对应openid的测试者
   * @param openId 小程序openid
   * @return 是否存在
   */
  Boolean isTester(String openId);
}
