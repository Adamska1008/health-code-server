package com.healthcode.healthcodeserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.healthcode.healthcodeserver.dao.IdentityApplicationDao;
import com.healthcode.healthcodeserver.dao.UserDao;
import com.healthcode.healthcodeserver.entity.IdentityApplication;
import com.healthcode.healthcodeserver.entity.User;
import com.healthcode.healthcodeserver.service.IdentityApplicationService;
import com.healthcode.healthcodeserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdentityApplicationServiceImpl extends ServiceImpl<IdentityApplicationDao,IdentityApplication> implements IdentityApplicationService {
  @Autowired
  IdentityApplicationDao identityApplicationDao;

  @Override
  public List<IdentityApplication> getTesterApplicationList() {
    QueryWrapper<IdentityApplication> wrapper = new QueryWrapper<>();
    wrapper.eq("apply_type", 0);
    return identityApplicationDao.selectList(wrapper);
  }

  @Override
  public Boolean hasApplicationRecord(String id) {
    QueryWrapper queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("applicant_person_id",id);
    return !identityApplicationDao.selectList(queryWrapper).isEmpty();
  }
}
