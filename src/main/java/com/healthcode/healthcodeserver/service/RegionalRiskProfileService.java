package com.healthcode.healthcodeserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.healthcode.healthcodeserver.entity.RegionalRiskProfile;

import java.util.List;

public interface RegionalRiskProfileService extends IService<RegionalRiskProfile> {

  /**
   * 获取某地区某种颜色健康码数量
   * @param position 地区位置，格式 省:市:区
   * @param codeStatus 健康码颜色 0：绿 1：黄 2：红
   * @return
   */
  long getCodeNumber(String position, int codeStatus);

  /**
   * 刷新某地风险情况
   * @param province
   * @param city
   * @param district
   */
  void refreshOneProfileByArea(String province,String city,String district);

  List<String> getSubArea(String province, String city);

  /**
   * 获取阳性数字
   * @param province
   * @param city
   * @param district
   * @return
   */
  int getPositiveNumber(String province, String city, String district);

  /**
   * 获取风险等级，0:常态化 1:低 2:中 3:高
   * @param province
   * @param city
   * @param district
   * @return
   */
  int getRiskLevel(String province, String city, String district);

  /**
   *
   * @param province
   * @param city
   * @param level
   * @return
   */
  int getSpecificLevelNumber(String province, String city, Integer level);
}
