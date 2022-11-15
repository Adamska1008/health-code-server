package com.healthcode.healthcodeserver.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.healthcode.healthcodeserver.entity.RemoteReporting;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RemoteReportingDao extends BaseMapper<RemoteReporting> {
}
