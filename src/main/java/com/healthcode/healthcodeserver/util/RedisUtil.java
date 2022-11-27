package com.healthcode.healthcodeserver.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class RedisUtil {
  @Resource
  private RedisTemplate<String, String> redisTemplate;

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

  public List<String> getAbnormalInfoIdList(String openId) {
    String key = openId + "abnormal_info";
    return redisTemplate.opsForList().range(key, 0, -1);
  }
}
