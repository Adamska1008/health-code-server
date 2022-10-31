package com.healthcode.healthcodeserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.healthcode.healthcodeserver.entity.NucleicAcidTestInfo;

import java.util.List;

public interface NucleicAcidTestInfoService extends IService<NucleicAcidTestInfo> {
  List<NucleicAcidTestInfo> getNucleicAcidTestInfoListByPersonId(String id);
}
