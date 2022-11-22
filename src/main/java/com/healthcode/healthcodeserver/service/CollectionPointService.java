package com.healthcode.healthcodeserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.healthcode.healthcodeserver.entity.CollectionPoint;

import java.util.List;

public interface CollectionPointService extends IService<CollectionPoint> {
  List<CollectionPoint> getByPage(int page, int size);
}
