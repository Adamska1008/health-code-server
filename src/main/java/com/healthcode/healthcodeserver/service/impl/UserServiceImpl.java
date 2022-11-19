package com.healthcode.healthcodeserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.healthcode.healthcodeserver.dao.UserDao;
import com.healthcode.healthcodeserver.entity.User;
import com.healthcode.healthcodeserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao,User> implements UserService {
  @Autowired
  private UserDao userDao;

  /**
   * 通过身份证号获取用户信息
   * @param personId 身份证号
   * @return 对应的User对象数据
   */
  @Override
  public User getUserInfoByPersonId(String personId) {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("person_id",personId);
    return userDao.selectOne(queryWrapper);
  }

  /**
   * 通过用户小程序openid获取用户信息
   * @param openId 用户小程序openid
   * @return 对应的User对象数据
   */
  public User getUserInfoByOpenId(String openId) {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("wx_openid",openId);
    return userDao.selectOne(queryWrapper);
  }

  /**
   * 插入用户信息
   * @param personId 身份证号
   * @param personName 姓名
   * @param phoneNumber 手机号
   * @param wxOpenId 微信openId
   * @param gender 性别
   * @return 返回BaseMapper默认的插入行的主键Id(该返回值不使用)
   */
  @Override
  public int insertUserInfo(String personId, String personName, String phoneNumber, String wxOpenId, String gender) {
    User user = new User();
    user.setPersonId(personId);
    user.setName(personName);
    int genderNumber = Integer.parseInt(gender);
    user.setGender((short)(genderNumber));
    user.setHealthCodeColor(0);
    user.setOpenId(wxOpenId);
    return userDao.insert(user);
  }
}
