package com.healthcode.healthcodeserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.healthcode.healthcodeserver.dao.RemoteReportingDao;
import com.healthcode.healthcodeserver.entity.RemoteReporting;
import com.healthcode.healthcodeserver.service.RemoteReportingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemoteReportingServiceImpl
        extends ServiceImpl<RemoteReportingDao, RemoteReporting>
        implements RemoteReportingService {
  @Autowired
  RemoteReportingDao remoteReportingDao;
}
