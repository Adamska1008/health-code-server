package com.healthcode.healthcodeserver.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.healthcode.healthcodeserver.entity.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountDao extends BaseMapper<Account> {
}
