package com.healthcode.healthcodeserver.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.healthcode.healthcodeserver.entity.Tester;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TesterDao extends BaseMapper<Tester> {
}
