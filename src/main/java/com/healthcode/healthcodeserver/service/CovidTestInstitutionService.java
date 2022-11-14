package com.healthcode.healthcodeserver.service;

public interface CovidTestInstitutionService {
  /**
   * 通过id获取机构名
   * @param id 机构id
   * @return 机构名
   */
  String getNameById(String id);
}
