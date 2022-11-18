package com.healthcode.healthcodeserver.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.healthcode.healthcodeserver.entity.AbnormalInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AbnormalInfoDao extends BaseMapper<AbnormalInfo> {
}
