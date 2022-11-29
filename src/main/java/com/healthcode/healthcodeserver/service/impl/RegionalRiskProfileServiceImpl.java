package com.healthcode.healthcodeserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.healthcode.healthcodeserver.dao.RegionalRiskProfileDao;
import com.healthcode.healthcodeserver.dao.UserDao;
import com.healthcode.healthcodeserver.entity.RegionalRiskProfile;
import com.healthcode.healthcodeserver.entity.User;
import com.healthcode.healthcodeserver.service.RegionalRiskProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegionalRiskProfileServiceImpl
        extends ServiceImpl<RegionalRiskProfileDao, RegionalRiskProfile>
        implements RegionalRiskProfileService {
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
  public long getCodeNumber(String position, int codeStatus) {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("health_code_color",codeStatus);
    queryWrapper.eq("position",position);
    return userDao.selectCount(queryWrapper);
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
    UpdateWrapper<RegionalRiskProfile> wrapper = new UpdateWrapper<>();
    wrapper.eq("province",province);
    wrapper.eq("city",city);
    wrapper.eq("district",district);
    wrapper.set("red_code_number", getCodeNumber(position, 2));
    wrapper.set("yellow_code_number", getCodeNumber(position, 1));
    //todo:设置感染人数、风险等级、风险区数量
    regionalRiskProfileDao.update(null, wrapper);
  }
}
