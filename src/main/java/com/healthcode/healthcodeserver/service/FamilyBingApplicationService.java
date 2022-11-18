package com.healthcode.healthcodeserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.healthcode.healthcodeserver.entity.FamilyBingApplication;

import java.util.List;

public interface FamilyBingApplicationService extends IService<FamilyBingApplication> {
  List<FamilyBingApplication> listByLimit(int limit);
}
