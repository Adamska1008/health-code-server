package com.healthcode.healthcodeserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.healthcode.healthcodeserver.entity.VenueCodeInfo;

public interface VenueCodeInfoService extends IService<VenueCodeInfo> {
  public String getVenueNameById(String id);
}
