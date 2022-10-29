package com.healthcode.healthcodeserver.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.healthcode.healthcodeserver.entity.Person;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface PersonDao extends BaseMapper<Person>  {
}
