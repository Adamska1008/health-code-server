package com.healthcode.healthcodeserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.healthcode.healthcodeserver.entity.TransferCodeInfo;

import java.util.List;

public interface TransferCodeInfoService extends IService<TransferCodeInfo> {
  public List<TransferCodeInfo> getNotTransferredByOpenId(String openId);
}
