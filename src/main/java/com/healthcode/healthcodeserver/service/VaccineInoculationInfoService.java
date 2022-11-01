package com.healthcode.healthcodeserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.healthcode.healthcodeserver.entity.VaccineInoculationInfo;

import java.util.List;

public interface VaccineInoculationInfoService extends IService<VaccineInoculationInfo> {
  public List<VaccineInoculationInfo> getVaccineInoculationInfoListByPersonId(String id);
}
