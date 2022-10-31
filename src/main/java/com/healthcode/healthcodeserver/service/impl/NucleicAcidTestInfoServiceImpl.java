package com.healthcode.healthcodeserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.healthcode.healthcodeserver.dao.NucleicAcidTestInfoDao;
import com.healthcode.healthcodeserver.entity.NucleicAcidTestInfo;
import com.healthcode.healthcodeserver.service.NucleicAcidTestInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NucleicAcidTestInfoServiceImpl extends ServiceImpl<NucleicAcidTestInfoDao, NucleicAcidTestInfo> implements NucleicAcidTestInfoService {
  @Autowired
  NucleicAcidTestInfoService nucleicAcidTestInfoService;
  @Override
  public List<NucleicAcidTestInfo> getNucleicAcidTestInfoListById(String id) {
    QueryWrapper queryWrapper = new QueryWrapper<>().eq("person_id",id);
    return nucleicAcidTestInfoService.list(queryWrapper);
  }
}
