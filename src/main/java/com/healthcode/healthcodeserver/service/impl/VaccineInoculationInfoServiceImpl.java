package com.healthcode.healthcodeserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.healthcode.healthcodeserver.dao.VaccineInoculationInfoDao;
import com.healthcode.healthcodeserver.entity.VaccineInoculationInfo;
import com.healthcode.healthcodeserver.service.VaccineInoculationInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaccineInoculationInfoServiceImpl extends ServiceImpl<VaccineInoculationInfoDao, VaccineInoculationInfo> implements VaccineInoculationInfoService {
  @Autowired
  VaccineInoculationInfoDao vaccineInoculationInfoDao;
  @Override
  public List<VaccineInoculationInfo> getInfoListByPersonId(String id) {
    QueryWrapper<VaccineInoculationInfo> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("person_id",id);
    return vaccineInoculationInfoDao.selectList(queryWrapper);
  }
}
