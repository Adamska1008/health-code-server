package com.healthcode.healthcodeserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.healthcode.healthcodeserver.entity.RegionalRiskProfile;

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
  public void refreshOneProfileByArea(String province,String city,String district);
}
