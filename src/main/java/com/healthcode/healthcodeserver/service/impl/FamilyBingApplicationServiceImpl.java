package com.healthcode.healthcodeserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.healthcode.healthcodeserver.entity.FamilyBingApplication;
import com.healthcode.healthcodeserver.dao.FamilyBingApplicationDao;
import com.healthcode.healthcodeserver.service.FamilyBingApplicationService;
import org.springframework.stereotype.Service;

@Service
public class FamilyBingApplicationServiceImpl extends ServiceImpl<FamilyBingApplicationDao, FamilyBingApplication> implements FamilyBingApplicationService {
}
