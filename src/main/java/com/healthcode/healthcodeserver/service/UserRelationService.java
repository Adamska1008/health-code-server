package com.healthcode.healthcodeserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.healthcode.healthcodeserver.entity.User;
import com.healthcode.healthcodeserver.entity.UserRelation;

public interface UserRelationService extends IService<UserRelation> {
  /**
   * 通过二人的身份证判断关系是否存在
   * @param personIdA 申请人
   * @param personIdB 被申请人
   * @return 是否存在
   */
  public UserRelation getRelationByTwoIds(String personIdA, String personIdB);
}
