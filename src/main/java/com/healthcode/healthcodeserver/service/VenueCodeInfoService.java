package com.healthcode.healthcodeserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.healthcode.healthcodeserver.entity.VenueCodeApplication;
import com.healthcode.healthcodeserver.entity.VenueCodeInfo;

import java.util.List;

public interface VenueCodeInfoService extends IService<VenueCodeInfo> {
  /**
   * 通过场所码id获取场所名称
   * @param id 场所码id
   * @return 场所码名称
   */
  String getVenueNameById(String id);

}
