package com.healthcode.healthcodeserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.healthcode.healthcodeserver.dao.VenueCodeApplicationDao;
import com.healthcode.healthcodeserver.entity.VenueCodeApplication;
import com.healthcode.healthcodeserver.service.VenueCodeApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueCodeApplicationServiceImpl
        extends ServiceImpl<VenueCodeApplicationDao, VenueCodeApplication>
        implements VenueCodeApplicationService {
  @Autowired
  VenueCodeApplicationDao venueCodeApplicationDao;

  /**
   * 通过申请人和地名信息获取场所码申请（保证一个人只能申请一个地名）
   * @param applicantName 申请人名字
   * @param placeName 申请地名
   * @return 场所码申请信息
   */
  @Override
  public VenueCodeApplication getApplicationByApplicantNameAndPlaceName(String applicantName,
                                                                        String placeName) {
    QueryWrapper<VenueCodeApplication> wrapper = new QueryWrapper<>();
    wrapper.eq("code_application_person_name", applicantName);
    wrapper.eq("code_application_name", placeName);
    return venueCodeApplicationDao.selectOne(wrapper);
  }

  @Override
  public List<VenueCodeApplication> getByPage(Integer page, Integer size) {
    Page<VenueCodeApplication> applicationPage = new Page<>(page, size);
    QueryWrapper<VenueCodeApplication> wrapper = new QueryWrapper<>();
    wrapper.lt("is_solved", 1);
    venueCodeApplicationDao.selectPage(applicationPage, wrapper);
    return applicationPage.getRecords();
  }
}
