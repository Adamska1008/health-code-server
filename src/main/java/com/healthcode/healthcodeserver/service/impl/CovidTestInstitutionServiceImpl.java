package com.healthcode.healthcodeserver.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.healthcode.healthcodeserver.dao.CovidTestInstitutionDao;
import com.healthcode.healthcodeserver.dao.NucleicAcidTestInfoDao;
import com.healthcode.healthcodeserver.entity.CovidTestInstitution;
import com.healthcode.healthcodeserver.entity.NucleicAcidTestInfo;
import com.healthcode.healthcodeserver.service.CovidTestInstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CovidTestInstitutionServiceImpl
        extends ServiceImpl<CovidTestInstitutionDao, CovidTestInstitution>
        implements CovidTestInstitutionService {
  @Autowired
  CovidTestInstitutionDao covidTestInstitutionDao;

  /**
   * 通过id获取机构名
   * @param id 机构id
   * @return 机构名
   */
  public String getNameById(String id) {
    CovidTestInstitution institution = covidTestInstitutionDao.selectById(id);
    return institution.getName();
  }
}
