package com.healthcode.healthcodeserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.healthcode.healthcodeserver.entity.IdentityApplication;

import java.util.List;

public interface IdentityApplicationService extends IService<IdentityApplication> {
  /**
   * 获取限定长度以内的申请列表
   * @param limit 限制长度
   * @return 申请列表
   */
  List<IdentityApplication> getTesterApplicationList(int limit);

  /**
   * 确认某人员是否有申请记录
   * @param personId 身份证号
   * @return 是否已申请
   */
  Boolean hasApplicationRecord(String personId);

  /**
   * 更新给定id的申请是否成功
   * @param id 申请id
   * @param isSucceed 是否成功
   */
  void updateApplicantProcessed(String id, Integer isSucceed);
}
