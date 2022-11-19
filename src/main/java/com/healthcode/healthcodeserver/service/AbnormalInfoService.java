package com.healthcode.healthcodeserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.healthcode.healthcodeserver.entity.AbnormalInfo;

import java.util.List;

public interface AbnormalInfoService extends IService<AbnormalInfo> {
  List<AbnormalInfo> listByLimit(int limit);

  List<AbnormalInfo> listByPage(int page, int size);
}
