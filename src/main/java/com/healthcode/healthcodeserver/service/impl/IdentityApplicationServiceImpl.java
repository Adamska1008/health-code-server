package com.healthcode.healthcodeserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.healthcode.healthcodeserver.dao.IdentityApplicationDao;
import com.healthcode.healthcodeserver.entity.IdentityApplication;
import com.healthcode.healthcodeserver.service.IdentityApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.desktop.QuitEvent;
import java.util.List;

@Service
public class IdentityApplicationServiceImpl
        extends ServiceImpl<IdentityApplicationDao,IdentityApplication>
        implements IdentityApplicationService {
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
   *
   * @param page
   * @param size
   * @return
   */
  @Override
  public List<IdentityApplication> getByPage(int page, int size) {
    Page<IdentityApplication> applicationPage = new Page<>(page, size);
    QueryWrapper<IdentityApplication> wrapper = new QueryWrapper<>();
    wrapper.lt("is_processed", 1);
    wrapper.eq("apply_type", 0);
    identityApplicationDao.selectPage(applicationPage, wrapper);
    return applicationPage.getRecords();
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

  /**
   * 获取某人的申请是否成功
   * @param openId
   * @return
   */
  @Override
  public boolean applicationSucceed(String openId) {
    QueryWrapper queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("open_id",openId);
    IdentityApplication identityApplication = identityApplicationDao.selectOne(queryWrapper);
    if (identityApplication.getIsSucceed()==1){
      return true;
    } else {
      return false;
    }
  }

  /**
   * 获取某人申请是否处理
   * @return
   */
  @Override
  public boolean applicationProcessed(String openId) {
    QueryWrapper queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("open_id",openId);
    IdentityApplication identityApplication = identityApplicationDao.selectOne(queryWrapper);
    if (identityApplication.getIsProcessed() == 1){
      return false;
    } else {
      return true;
    }
  }

  /**
   * 根据openId判断是否有申请记录
   * @param openId
   * @return
   */
  @Override
  public boolean hasApplicationRecordByOpenId(String openId) {
    QueryWrapper queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("open_id",openId);
    return !identityApplicationDao.selectList(queryWrapper).isEmpty();
  }
}
