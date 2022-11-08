package com.healthcode.healthcodeserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.healthcode.healthcodeserver.dao.IdentityApplicationDao;
import com.healthcode.healthcodeserver.entity.IdentityApplication;
import com.healthcode.healthcodeserver.service.IdentityApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdentityApplicationServiceImpl extends ServiceImpl<IdentityApplicationDao,IdentityApplication> implements IdentityApplicationService {
  @Autowired
  IdentityApplicationDao identityApplicationDao;

  @Override
  public List<IdentityApplication> getTesterApplicationList(int limit) {
    QueryWrapper<IdentityApplication> wrapper = new QueryWrapper<>();
    wrapper.eq("apply_type", 0)
            .last("LIMIT "+limit);
    return identityApplicationDao.selectList(wrapper);
  }

  @Override
  public Boolean hasApplicationRecord(String id) {
    QueryWrapper<IdentityApplication> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("applicant_person_id",id);
    return !identityApplicationDao.selectList(queryWrapper).isEmpty();
  }

  @Override
  public void updateApplicantProcessed(String id, Integer isSucceed) {
    UpdateWrapper<IdentityApplication> wrapper = new UpdateWrapper<>();
    wrapper.eq("applicant_person_id", id)
            .set("is_processed", 1)
            .set("is_succeed", isSucceed);
    identityApplicationDao.update(null, wrapper);
  }
}
