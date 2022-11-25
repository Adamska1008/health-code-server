package com.healthcode.healthcodeserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.healthcode.healthcodeserver.entity.RemoteReporting;

import java.util.List;

public interface RemoteReportingService extends IService<RemoteReporting> {
  /**
   * 有分页的获取列表
   * @param page 第几页
   * @param size 页大小
   * @return 元素列表
   */
  List<RemoteReporting> listByPage(int page, int size);
}
