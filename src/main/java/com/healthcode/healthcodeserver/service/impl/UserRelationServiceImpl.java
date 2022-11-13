package com.healthcode.healthcodeserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.healthcode.healthcodeserver.dao.UserRelationDao;
import com.healthcode.healthcodeserver.entity.UserRelation;
import com.healthcode.healthcodeserver.service.UserRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRelationServiceImpl extends ServiceImpl<UserRelationDao, UserRelation> implements UserRelationService {
  @Autowired
  UserRelationDao userRelationDao;

  public UserRelation getRelationByTwoIds(String personIdA, String personIdB) {
    QueryWrapper<UserRelation> wrapper = new QueryWrapper<>();
    wrapper.eq("person_id_a", personIdA);
    wrapper.eq("person_id_b", personIdB);
    return  userRelationDao.selectOne(wrapper);
  }
}
