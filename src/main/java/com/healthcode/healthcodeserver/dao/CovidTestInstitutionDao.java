package com.healthcode.healthcodeserver.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.healthcode.healthcodeserver.entity.CovidTestInstitution;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CovidTestInstitutionDao extends BaseMapper<CovidTestInstitution> {
}
