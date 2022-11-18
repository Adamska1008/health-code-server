package com.healthcode.healthcodeserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.healthcode.healthcodeserver.entity.FamilyBingApplication;
import com.healthcode.healthcodeserver.dao.FamilyBingApplicationDao;
import com.healthcode.healthcodeserver.service.FamilyBingApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamilyBingApplicationServiceImpl
        extends ServiceImpl<FamilyBingApplicationDao, FamilyBingApplication>
        implements FamilyBingApplicationService {
  @Autowired
  FamilyBingApplicationDao familyBingApplicationDao;

  @Override
  public List<FamilyBingApplication> listByLimit(int limit) {
    QueryWrapper<FamilyBingApplication> wrapper = new QueryWrapper<>();
    wrapper.orderByAsc("is_succeed");
    wrapper.orderByAsc("is_processed");
    wrapper.last("LIMIT "+limit);
    return familyBingApplicationDao.selectList(wrapper);
  }
}
