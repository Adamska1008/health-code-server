package com.healthcode.healthcodeserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.healthcode.healthcodeserver.entity.VaccineInoculationInfo;

import java.util.List;

public interface VaccineInoculationInfoService extends IService<VaccineInoculationInfo> {
  /**
   * 通过personId获取信息列表
   * @param id 身份证号
   * @return 疫苗接种信息列表
   */
  List<VaccineInoculationInfo> getInfoListByPersonId(String personId);
}
