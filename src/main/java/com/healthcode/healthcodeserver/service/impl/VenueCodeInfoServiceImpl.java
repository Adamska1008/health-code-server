package com.healthcode.healthcodeserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.healthcode.healthcodeserver.dao.VenueCodeInfoDao;
import com.healthcode.healthcodeserver.entity.VenueCodeInfo;
import com.healthcode.healthcodeserver.service.VenueCodeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VenueCodeInfoServiceImpl
        extends ServiceImpl<VenueCodeInfoDao, VenueCodeInfo>
        implements VenueCodeInfoService {
  @Autowired
  VenueCodeInfoDao venueCodeInfoDao;

  /**
   * 通过场所码id获取场所名称
   * @param id 场所码id
   * @return 场所码名称
   */
  @Override
  public String getVenueNameById(String id) {
    VenueCodeInfo info = venueCodeInfoDao.selectById(id);
    return info.getName();
  }
}
