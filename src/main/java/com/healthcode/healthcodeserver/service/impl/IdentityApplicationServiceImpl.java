package com.healthcode.healthcodeserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.healthcode.healthcodeserver.dao.IdentityApplicationDao;
import com.healthcode.healthcodeserver.entity.IdentityApplication;
import com.healthcode.healthcodeserver.service.IdentityApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdentityApplicationServiceImpl implements IdentityApplicationService {
  @Autowired
  IdentityApplicationDao identityApplicationDao;

  @Override
  public List<IdentityApplication> getTesterApplicationList() {
    QueryWrapper<IdentityApplication> wrapper = new QueryWrapper<>();
    wrapper.eq("apply_type", 0);
    return identityApplicationDao.selectList(wrapper);
  }
}
