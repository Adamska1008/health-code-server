package com.healthcode.healthcodeserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.healthcode.healthcodeserver.dao.TesterDao;
import com.healthcode.healthcodeserver.entity.Tester;
import com.healthcode.healthcodeserver.service.TesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TesterServiceImpl
        extends ServiceImpl<TesterDao, Tester>
        implements TesterService {
  @Autowired
  TesterDao testerDao;

  /**
   * 查询是否存在对应openid的测试者
   * @param openId 小程序openid
   * @return 是否存在
   */
  @Override
  public Boolean isTester(String openId) {
    QueryWrapper<Tester> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("open_id",openId);
    return !testerDao.selectList(queryWrapper).isEmpty();
  }
}
