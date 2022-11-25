package com.healthcode.healthcodeserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.healthcode.healthcodeserver.dao.AbnormalInfoDao;
import com.healthcode.healthcodeserver.entity.AbnormalInfo;
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

  /**
   * 有分页的获取列表
   * @param page 第几页
   * @param size 页大小
   * @return 元素列表
   */
  @Override
  public List<AbnormalInfo> listByPage(int page, int size) {
    Page<AbnormalInfo> infoPage = new Page<>(page, size);
    QueryWrapper<AbnormalInfo> wrapper = new QueryWrapper<>();
    wrapper.lt("is_investigated", 1);
    abnormalInfoDao.selectPage(infoPage, wrapper);
    return infoPage.getRecords();
  }
}
