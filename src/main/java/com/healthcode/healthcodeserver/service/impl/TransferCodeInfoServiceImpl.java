package com.healthcode.healthcodeserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.healthcode.healthcodeserver.dao.TransferCodeInfoDao;
import com.healthcode.healthcodeserver.entity.TransferCodeInfo;
import com.healthcode.healthcodeserver.service.TransferCodeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferCodeInfoServiceImpl
        extends ServiceImpl<TransferCodeInfoDao, TransferCodeInfo>
        implements TransferCodeInfoService {
  @Autowired
  TransferCodeInfoDao transferCodeInfoDao;

  /**
   * 通过openid获取没有转运的试剂信息
   * @param openId 小程序openid
   * @return 没有转运的试剂信息列表
   */
  @Override
  public List<TransferCodeInfo> getNotTransferredByOpenId(String openId) {
    QueryWrapper<TransferCodeInfo> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("tester_open_id",openId);
    queryWrapper.eq("is_transferred",0);
    return transferCodeInfoDao.selectList(queryWrapper);
  }

  @Override
  public int transferList(List list) {
    UpdateWrapper updateWrapper = new UpdateWrapper();
    updateWrapper.in("transfer_code",list);
    updateWrapper.set("is_transferred",1);
    return transferCodeInfoDao.update(null,updateWrapper);
  }
}
