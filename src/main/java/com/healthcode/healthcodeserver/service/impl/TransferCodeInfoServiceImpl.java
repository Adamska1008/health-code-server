package com.healthcode.healthcodeserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.healthcode.healthcodeserver.dao.TransferCodeInfoDao;
import com.healthcode.healthcodeserver.entity.TransferCodeInfo;
import com.healthcode.healthcodeserver.service.TransferCodeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferCodeInfoServiceImpl extends ServiceImpl<TransferCodeInfoDao, TransferCodeInfo> implements TransferCodeInfoService {
  @Autowired
  TransferCodeInfoDao transferCodeInfoDao;

  @Override
  public List<TransferCodeInfo> getNotTransferredByOpenId(String openId) {
    QueryWrapper queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("tester_open_id",openId);
    queryWrapper.eq("is_transferred",0);
    return transferCodeInfoDao.selectList(queryWrapper);
  }
}
