package com.healthcode.healthcodeserver.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("t_regional_risk_profile")
public class RegionalRiskProfile {
  private String profileId;
  private String province;
  private String city;
  private String district;
  private String riskLevel;
  private String highRiskNumber;
  private String generalRiskNumber;
  private String redCodeNumber;
  private String yellowCodeNumber;
  private String infectedNumber;
}
