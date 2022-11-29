package com.healthcode.healthcodeserver.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.healthcode.healthcodeserver.entity.RegionalRiskProfile;
import com.healthcode.healthcodeserver.service.RegionalRiskProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Component
public class RedisUtil {
  @Resource
  private RedisTemplate<String, String> redisTemplate;
  @Autowired
  private RegionalRiskProfileService regionalRiskProfileService;
  private static final long riskProfileExpireTime = 300; // unit: s

  /**
   * 异地报备添加id
   * @param openId
   * @param remoteReportId
   */
  public void addRemoteReport(String openId, String remoteReportId) {
    String key = openId + "remote_report";
    redisTemplate.opsForList().rightPush(key, remoteReportId);
  }

  /**
   * 获取对应异地报备id列表
   * @param openId
   * @return
   */
  public List<String> getRemoteReportIdList(String openId) {
    String key = openId + "remote_report";
    return redisTemplate.opsForList().range(key, 0, -1);
  }

  /**
   * 场所码申请添加id
   * @param openId
   * @param applicationId
   */
  public void addVenueCodeApplication(String openId, String applicationId) {
    String key = openId + "venue_code_application";
    redisTemplate.opsForList().rightPush(key, applicationId);
  }

  /**
   * 获取对应场所码id列表
   * @param openId
   * @return
   */
  public List<String> getVenueCodeApplicationIdList(String openId) {
    String key = openId + "venue_code_application";
    return redisTemplate.opsForList().range(key, 0, -1);
  }

  /**
   *
   * @param openId
   * @param applicationId
   */
  public void addFamilyBindingApplication(String openId, String applicationId) {
    String key = openId + "family_binding_application";
    redisTemplate.opsForList().rightPush(key, applicationId);
  }

  /**
   *
   * @param openId
   * @return
   */
  public List<String> getFamilyBindingApplicationIdList(String openId) {
    String key = openId + "family_binding_application";
    return redisTemplate.opsForList().range(key, 0, -1);
  }

  /**
   *
   * @param openId
   * @param applicationId
   */
  public void addAbnormalInfo(String openId, String applicationId) {
    String key = openId + "abnormal_info";
    redisTemplate.opsForList().rightPush(key, applicationId);
  }

  /**
   *
   * @param openId
   * @return
   */
  public List<String> getAbnormalInfoIdList(String openId) {
    String key = openId + "abnormal_info";
    return redisTemplate.opsForList().range(key, 0, -1);
  }

  /**
   * 获取整体风险情况概况，
   * @return 指定等级，地区的风险地区数量
   */
  public Integer getOverallRiskLevel(String province, String city, int level) {
    Map<Integer, String> levelToStr = new HashMap<>() {{
      put(1, "low_level_number");
      put(2, "medium_level_number");
      put(3, "high_level_number");
    }};
    String key = levelToStr.get(level);
    if (province != null) { // 不指定省份与城市
      key = key + "_" + province;
      if (city != null) {
        key = key + "_" + city;
      }
    }
    if (Boolean.TRUE.equals(redisTemplate.hasKey(key))) {
      return Integer.valueOf(Objects.requireNonNull(redisTemplate.opsForValue().get(key)));
    } else {
      Integer value = regionalRiskProfileService
              .getSpecificLevelNumber(null, null, level);
      redisTemplate.opsForValue().set(key, String.valueOf(value));
      redisTemplate.expire(key, riskProfileExpireTime, TimeUnit.SECONDS);
      return value;
    }
  }
}
