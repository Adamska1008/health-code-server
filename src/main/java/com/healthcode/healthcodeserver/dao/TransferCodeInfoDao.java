package com.healthcode.healthcodeserver.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.healthcode.healthcodeserver.entity.TransferCodeInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TransferCodeInfoDao extends BaseMapper<TransferCodeInfo> {
}
