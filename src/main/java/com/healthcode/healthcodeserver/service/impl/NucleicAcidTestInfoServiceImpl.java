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

  /**
   * 通过身份证获取核酸检测信息表
   * @param personId 身份证
   * @return 核酸检测信息表
   */
  @Override
  public List<NucleicAcidTestInfo> getNucleicAcidTestInfoListByPersonId(String personId) {
    QueryWrapper<NucleicAcidTestInfo> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("person_id", personId);
    return nucleicAcidTestInfoDao.selectList(queryWrapper);
  }

  /**
   * 通过身份证号获取最新测试信息
   * @param personId 身份证号
   * @return 最新测试信息（一条）
   */
  @Override
  public NucleicAcidTestInfo getLatestTestInfoByPersonId(String personId) {
    QueryWrapper<NucleicAcidTestInfo> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("person_id", personId);
    queryWrapper.orderByDesc("test_time");
    queryWrapper.last("LIMIT 1");
    return nucleicAcidTestInfoDao.selectOne(queryWrapper);
  }

  /**
   * 转运码是否存在
   * @param transferCode 转运码
   * @return 是否存在
   */
  @Override
  public boolean transferCodeExists(String transferCode) {
    QueryWrapper<NucleicAcidTestInfo> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("transfer_code",transferCode);
    return !nucleicAcidTestInfoDao.selectList(queryWrapper).isEmpty();
  }
}
