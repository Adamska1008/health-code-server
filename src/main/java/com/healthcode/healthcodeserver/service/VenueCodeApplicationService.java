package com.healthcode.healthcodeserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.healthcode.healthcodeserver.entity.VenueCodeApplication;

public interface VenueCodeApplicationService extends IService<VenueCodeApplication> {
  VenueCodeApplication getApplicationByApplicantNameAndPlaceName(String applicantName, String placeName);
}
