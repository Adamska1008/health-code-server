package com.healthcode.healthcodeserver.service;

import com.healthcode.healthcodeserver.entity.IdentityApplication;

import java.util.List;

public interface IdentityApplicationService {
  List<IdentityApplication> getTesterApplicationList();
}
