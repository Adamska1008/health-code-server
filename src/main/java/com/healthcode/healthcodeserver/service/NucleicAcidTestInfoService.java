package com.healthcode.healthcodeserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.healthcode.healthcodeserver.entity.NucleicAcidTestInfo;

import java.util.List;

public interface NucleicAcidTestInfoService extends IService<NucleicAcidTestInfo> {
  /**
   * 通过身份证获取核酸检测信息表
   * @param personId 身份证
   * @return 核酸检测信息表
   */
  List<NucleicAcidTestInfo> getNucleicAcidTestInfoListByPersonId(String personId);

  /**
   * 通过身份证号获取最新测试信息
   * @param personId 身份证号
   * @return 最新测试信息（一条）
   */
  NucleicAcidTestInfo getLatestTestInfoByPersonId(String personId);


  /**
   * 转运码是否存在
   * @param transferCode 转运码
   * @return 是否存在
   */
  boolean transferCodeExists(String transferCode);
}
