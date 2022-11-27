package com.healthcode.healthcodeserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.healthcode.healthcodeserver.dao.RegionalRiskProfileDao;
import com.healthcode.healthcodeserver.dao.UserDao;
import com.healthcode.healthcodeserver.entity.RegionalRiskProfile;
import com.healthcode.healthcodeserver.entity.User;
import com.healthcode.healthcodeserver.service.RegionalRiskProfileService;
import com.healthcode.healthcodeserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RegionalRiskProfileServiceImpl extends ServiceImpl<RegionalRiskProfileDao, RegionalRiskProfile> implements RegionalRiskProfileService {
  @Autowired
  RegionalRiskProfileDao regionalRiskProfileDao;

  @Autowired
  UserDao userDao;

  /**
   * 获取某地区某种颜色健康码数量
   * @param position 地区位置，格式 省:市:区
   * @param codeStatus 健康码颜色 0：绿 1：黄 2：红
   * @return
   */
  @Override
  public int getCodeNumber(String position, int codeStatus) {
    QueryWrapper queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("health_code_color",codeStatus);
    queryWrapper.eq("position",position);
    List<User> list = userDao.selectList(queryWrapper);
    return list.size();
  }

  /**
   * 刷新某地风险情况
   * @param province
   * @param city
   * @param district
   */
  @Override
  public void refreshOneProfileByArea(String province, String city, String district) {
    String position = province+":"+city+":"+district;
    QueryWrapper queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("province",province);
    queryWrapper.eq("city",city);
    queryWrapper.eq("district",district);
    RegionalRiskProfile regionalRiskProfile = regionalRiskProfileDao.selectOne(queryWrapper);
    regionalRiskProfile.setRedCodeNumber(String.valueOf(getCodeNumber(position,2)));
    regionalRiskProfile.setYellowCodeNumber(String.valueOf(getCodeNumber(position,1)));
    //todo:设置感染人数、风险等级、风险区数量
    regionalRiskProfileDao.updateById(regionalRiskProfile);
  }
}
