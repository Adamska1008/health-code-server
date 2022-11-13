package com.healthcode.healthcodeserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.healthcode.healthcodeserver.entity.User;
import com.healthcode.healthcodeserver.entity.UserRelation;

public interface UserRelationService extends IService<UserRelation> {
  public UserRelation getRelationByTwoIds(String personIdA, String personIdB);
}
