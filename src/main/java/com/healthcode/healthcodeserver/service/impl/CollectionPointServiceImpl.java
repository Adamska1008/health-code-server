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

  @Override
  public List<CollectionPoint> getByPage(int page, int size) {
    Page<CollectionPoint> pointPage = new Page<>(page, size);
    collectionPointDao.selectPage(pointPage, null);
    return pointPage.getRecords();
  }
}
