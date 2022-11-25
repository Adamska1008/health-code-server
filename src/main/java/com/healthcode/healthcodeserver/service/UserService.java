package com.healthcode.healthcodeserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.healthcode.healthcodeserver.entity.User;

public interface UserService extends IService<User> {
  /**
   * 通过身份证号获取用户信息
   * @param personId 身份证号
   * @return 对应的User对象数据
   */
  User getByPersonId(String personId);

  /**
   * 通过用户小程序openid获取用户信息
   * @param openId 用户小程序openid
   * @return 对应的User对象数据
   */
  User getByOpenId(String openId);

  /**
   * 插入用户信息
   * @param personId 身份证号
   * @param personName 姓名
   * @param phoneNumber 手机号
   * @param wxOpenId 微信openId
   * @param gender 性别
   * @return 返回BaseMapper默认的插入行的主键Id(该返回值不使用)
   */
  int insertUserInfo(String personId,String personName,
                     String phoneNumber,String wxOpenId,String gender);
}
