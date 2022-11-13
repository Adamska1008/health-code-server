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
public class NucleicAcidTestInfoServiceImpl
        extends ServiceImpl<NucleicAcidTestInfoDao, NucleicAcidTestInfo>
        implements NucleicAcidTestInfoService {
  @Autowired
  private NucleicAcidTestInfoDao nucleicAcidTestInfoDao;

  @Override
  public List<NucleicAcidTestInfo> getNucleicAcidTestInfoListByPersonId(String personId) {
    QueryWrapper<NucleicAcidTestInfo> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("person_id", personId);
    return nucleicAcidTestInfoDao.selectList(queryWrapper);
  }

  @Override
  public NucleicAcidTestInfo getLatestTestInfoByPersonId(String personId) {
    QueryWrapper<NucleicAcidTestInfo> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("person_id", personId);
    queryWrapper.orderByDesc("test_time");
    queryWrapper.last("LIMIT 1");
    return nucleicAcidTestInfoDao.selectOne(queryWrapper);
  }

  @Override
  public boolean transferCodeExists(String transferCode) {
    QueryWrapper queryWrapper = new QueryWrapper();
    queryWrapper.eq("transfer_code",transferCode);
    return !nucleicAcidTestInfoDao.selectList(queryWrapper).isEmpty();
  }
}
