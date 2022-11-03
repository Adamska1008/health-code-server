package com.healthcode.healthcodeserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.healthcode.healthcodeserver.entity.VaccineInoculationInfo;

import java.util.List;

public interface VaccineInoculationInfoService extends IService<VaccineInoculationInfo> {
  List<VaccineInoculationInfo> getInfoListByPersonId(String personId);
}
