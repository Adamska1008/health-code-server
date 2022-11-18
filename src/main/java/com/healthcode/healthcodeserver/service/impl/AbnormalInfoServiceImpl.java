package com.healthcode.healthcodeserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.healthcode.healthcodeserver.dao.AbnormalInfoDao;
import com.healthcode.healthcodeserver.entity.AbnormalInfo;
import com.healthcode.healthcodeserver.entity.RemoteReporting;
import com.healthcode.healthcodeserver.service.AbnormalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbnormalInfoServiceImpl
        extends ServiceImpl<AbnormalInfoDao, AbnormalInfo>
        implements AbnormalInfoService {
  @Autowired
  AbnormalInfoDao abnormalInfoDao;

  @Override
  public List<AbnormalInfo> listByLimit(int limit) {
    QueryWrapper<AbnormalInfo> wrapper = new QueryWrapper<>();
    wrapper.orderByAsc("is_investigated");
    wrapper.orderByAsc("is_processed");
    wrapper.last("LIMIT "+limit);
    return abnormalInfoDao.selectList(wrapper);
  }
}
