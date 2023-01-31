package com.healthcode.healthcodeserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.healthcode.healthcodeserver.dao.RemoteReportingDao;
import com.healthcode.healthcodeserver.entity.RemoteReporting;
import com.healthcode.healthcodeserver.service.RemoteReportingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.Remote;
import java.util.List;

@Service
public class RemoteReportingServiceImpl
        extends ServiceImpl<RemoteReportingDao, RemoteReporting>
        implements RemoteReportingService {
  @Autowired
  RemoteReportingDao remoteReportingDao;

  /**
   * 有分页的获取列表
   * @param page 第几页
   * @param size 页大小
   * @return 元素列表
   */
  @Override
  public List<RemoteReporting> listByPage(int page, int size) {
    Page<RemoteReporting> reportingPage = new Page<>(page, size);
    QueryWrapper<RemoteReporting> wrapper = new QueryWrapper<>();
    wrapper.lt("is_checked", 1);
    remoteReportingDao.selectPage(reportingPage, wrapper);
    return reportingPage.getRecords();
  }
}
