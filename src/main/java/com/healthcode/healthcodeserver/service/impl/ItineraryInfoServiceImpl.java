package com.healthcode.healthcodeserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.healthcode.healthcodeserver.entity.ItineraryInfo;
import com.healthcode.healthcodeserver.dao.ItineraryInfoDao;
import com.healthcode.healthcodeserver.service.ItineraryInfoService;
import org.springframework.stereotype.Service;

@Service
public class ItineraryInfoServiceImpl
        extends ServiceImpl<ItineraryInfoDao, ItineraryInfo>
        implements ItineraryInfoService {
}
