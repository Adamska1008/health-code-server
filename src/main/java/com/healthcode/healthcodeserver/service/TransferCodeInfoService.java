package com.healthcode.healthcodeserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.healthcode.healthcodeserver.entity.TransferCodeInfo;

import java.util.List;

public interface TransferCodeInfoService extends IService<TransferCodeInfo> {
  /**
   * 通过openid获取没有转运的试剂信息
   * @param openId 小程序openid
   * @return 没有转运的试剂信息列表
   */
  List<TransferCodeInfo> getNotTransferredByOpenId(String openId);
}
