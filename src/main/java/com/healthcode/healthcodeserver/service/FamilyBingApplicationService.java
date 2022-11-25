package com.healthcode.healthcodeserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.healthcode.healthcodeserver.entity.FamilyBingApplication;

import java.util.List;

public interface FamilyBingApplicationService extends IService<FamilyBingApplication> {
  /**
   * 有分页的获取列表
   * @param page 第几页
   * @param size 页大小
   * @return 元素列表
   */
  List<FamilyBingApplication> listByPage(Integer page, Integer size);
}
