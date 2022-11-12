package com.healthcode.healthcodeserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.healthcode.healthcodeserver.dao.VenueCodeApplicationDao;
import com.healthcode.healthcodeserver.entity.VenueCodeApplication;
import com.healthcode.healthcodeserver.service.VenueCodeApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VenueCodeApplicationServiceImpl extends ServiceImpl<VenueCodeApplicationDao, VenueCodeApplication> implements VenueCodeApplicationService {
  @Autowired
  VenueCodeApplicationDao venueCodeApplicationDao;

  @Override
  public VenueCodeApplication getApplicationByApplicantNameAndPlaceName(String applicantName, String placeName) {
    return null;
  }
}
