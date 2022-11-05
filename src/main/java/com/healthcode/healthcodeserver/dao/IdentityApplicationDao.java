package com.healthcode.healthcodeserver.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.healthcode.healthcodeserver.entity.IdentityApplication;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IdentityApplicationDao extends BaseMapper<IdentityApplication> {
}
