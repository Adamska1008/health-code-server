package com.healthcode.healthcodeserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.healthcode.healthcodeserver.entity.RemoteReporting;

import java.util.List;

public interface RemoteReportingService extends IService<RemoteReporting> {
  public List<RemoteReporting> listByLimit(int limit);
}
