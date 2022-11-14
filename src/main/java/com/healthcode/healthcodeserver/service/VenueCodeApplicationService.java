package com.healthcode.healthcodeserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.healthcode.healthcodeserver.entity.VenueCodeApplication;

public interface VenueCodeApplicationService extends IService<VenueCodeApplication> {
  /**
   * 通过申请人和地名信息获取场所码申请（保证一个人只能申请一个地名）
   * @param applicantName 申请人名字
   * @param placeName 申请地名
   * @return 场所码申请信息
   */
  VenueCodeApplication getApplicationByApplicantNameAndPlaceName(String applicantName, String placeName);
}
