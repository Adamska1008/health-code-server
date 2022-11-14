package com.healthcode.healthcodeserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

  /**
   * 获取限定长度以内的申请列表
   * @param limit 限制长度
   * @return 申请列表
   */
  @Override
  public List<IdentityApplication> getTesterApplicationList(int limit) {
    QueryWrapper<IdentityApplication> wrapper = new QueryWrapper<>();
    wrapper.eq("apply_type", 0)
            .orderByAsc("is_processed")
            .last("LIMIT "+limit);
    return identityApplicationDao.selectList(wrapper);
  }

  /**
   * 确认某人员是否有申请记录
   * @param personId 身份证号
   * @return 是否已申请
   */
  @Override
  public Boolean hasApplicationRecord(String personId) {
    QueryWrapper<IdentityApplication> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("applicant_person_id", personId);
    return !identityApplicationDao.selectList(queryWrapper).isEmpty();
  }

  /**
   * 更新给定id的申请是否成功
   * @param id 申请id
   * @param isSucceed 是否成功
   */
  @Override
  public void updateApplicantProcessed(String id, Integer isSucceed) {
    UpdateWrapper<IdentityApplication> wrapper = new UpdateWrapper<>();
    wrapper.eq("applicant_person_id", id)
            .set("is_processed", 1)
            .set("is_succeed", isSucceed);
    identityApplicationDao.update(null, wrapper);
  }
}
