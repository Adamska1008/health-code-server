package com.healthcode.healthcodeserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.healthcode.healthcodeserver.dao.CollectionPointDao;
import com.healthcode.healthcodeserver.entity.CollectionPoint;
import com.healthcode.healthcodeserver.service.CollectionPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionPointServiceImpl
        extends ServiceImpl<CollectionPointDao, CollectionPoint>
        implements CollectionPointService {
  @Autowired
  CollectionPointDao collectionPointDao;

  /**
   * 有分页的获取列表
   * @param page 第几页
   * @param size 页大小
   * @return 元素列表
   */
  @Override
  public List<CollectionPoint> getByPage(int page, int size) {
    Page<CollectionPoint> pointPage = new Page<>(page, size);
    collectionPointDao.selectPage(pointPage, null);
    return pointPage.getRecords();
  }

  @Override
  public List<CollectionPoint> getByDistrict(String province, String city, String district) {
    String position = province + ":" + city + ":" + district;
    QueryWrapper<CollectionPoint> wrapper = new QueryWrapper<>();
    wrapper.eq("collection_point_position", position);
    return collectionPointDao.selectList(wrapper);
  }
}
