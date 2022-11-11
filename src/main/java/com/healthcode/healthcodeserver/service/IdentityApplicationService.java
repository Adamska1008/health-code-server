package com.healthcode.healthcodeserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.healthcode.healthcodeserver.entity.IdentityApplication;

import java.util.List;

public interface IdentityApplicationService extends IService<IdentityApplication> {
  List<IdentityApplication> getTesterApplicationList(int limit);

  Boolean hasApplicationRecord(String personId);

  void updateApplicantProcessed(String id, Integer isSucceed);
}
