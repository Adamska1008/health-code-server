package com.healthcode.healthcodeserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@TableName("t_regional_risk_profile")
public class RegionalRiskProfile {
  @TableId(type = IdType.ASSIGN_ID)
  @JsonProperty("profile_id")
  private String profileId;
  private String province;
  private String city;
  private String district;
  @JsonProperty("risk_level")
  private Integer riskLevel;
  @JsonProperty("red_code_number")
  private Integer redCodeNumber;
  @JsonProperty("yellow_code_number")
  private Integer yellowCodeNumber;
  @JsonProperty("positive_number")
  private Integer positiveNumber;

  public RegionalRiskProfile() {
  }

  public RegionalRiskProfile(String profileId,
                             String province,
                             String city,
                             String district,
                             Integer riskLevel,
                             Integer redCodeNumber,
                             Integer yellowCodeNumber,
                             Integer positiveNumber) {
    this.profileId = profileId;
    this.province = province;
    this.city = city;
    this.district = district;
    this.riskLevel = riskLevel;
    this.redCodeNumber = redCodeNumber;
    this.yellowCodeNumber = yellowCodeNumber;
    this.positiveNumber = positiveNumber;
  }

  public Integer getRiskLevel() {
    return riskLevel;
  }

  public Integer getPositiveNumber() {
    return positiveNumber;
  }

  public Integer getRedCodeNumber() {
    return redCodeNumber;
  }

  public Integer getYellowCodeNumber() {
    return yellowCodeNumber;
  }

  public String getCity() {
    return city;
  }

  public String getDistrict() {
    return district;
  }

  public String getProfileId() {
    return profileId;
  }

  public String getProvince() {
    return province;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public void setDistrict(String district) {
    this.district = district;
  }

  public void setPositiveNumber(Integer positiveNumber) {
    this.positiveNumber = positiveNumber;
  }

  public void setProfileId(String profileId) {
    this.profileId = profileId;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public void setRedCodeNumber(Integer redCodeNumber) {
    this.redCodeNumber = redCodeNumber;
  }

  public void setRiskLevel(Integer riskLevel) {
    this.riskLevel = riskLevel;
  }

  public void setYellowCodeNumber(Integer yellowCodeNumber) {
    this.yellowCodeNumber = yellowCodeNumber;
  }

  @Override
  public String toString() {
    return "RegionalRiskProfile{" +
            "profileId='" + profileId + '\'' +
            ", province='" + province + '\'' +
            ", city='" + city + '\'' +
            ", district='" + district + '\'' +
            ", riskLevel=" + riskLevel +
            ", redCodeNumber=" + redCodeNumber +
            ", yellowCodeNumber=" + yellowCodeNumber +
            ", positiveNumber=" + positiveNumber +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RegionalRiskProfile that = (RegionalRiskProfile) o;
    return Objects.equals(profileId, that.profileId) && Objects.equals(province, that.province) && Objects.equals(city, that.city) && Objects.equals(district, that.district) && Objects.equals(riskLevel, that.riskLevel) && Objects.equals(redCodeNumber, that.redCodeNumber) && Objects.equals(yellowCodeNumber, that.yellowCodeNumber) && Objects.equals(positiveNumber, that.positiveNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(profileId, province, city, district, riskLevel, redCodeNumber, yellowCodeNumber, positiveNumber);
  }
}
