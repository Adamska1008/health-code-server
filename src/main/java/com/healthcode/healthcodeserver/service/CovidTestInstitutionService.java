package com.healthcode.healthcodeserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.healthcode.healthcodeserver.entity.CovidTestInstitution;

public interface CovidTestInstitutionService extends IService<CovidTestInstitution> {
  /**
   * 通过id获取机构名
   * @param id 机构id
   * @return 机构名
   */
  String getNameById(String id);
}
