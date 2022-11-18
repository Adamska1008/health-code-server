package com.healthcode.healthcodeserver.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@TableName("t_identity_application")
public class IdentityApplication {
  @TableId(value = "application_id", type = IdType.ASSIGN_ID)
  @JsonProperty(value = "application_id")
  private String id;
  @JsonProperty(value = "openid")
  private String openId;
  @JsonProperty(value = "applicant_name")
  private String applicantName;
  @JsonProperty(value = "applicant_person_id")
  private String applicantPersonId;
  @JsonProperty(value = "applicant_phone")
  private String applicantPhone;
  @TableField(value = "additional_information")
  @JsonProperty(value = "additional_information")
  private String additionalInfo;
  // 0:核酸检测人员 1: 政务管理人员
  @TableField("apply_type")
  @JsonProperty(value = "apply_type")
  private Integer type;
  @JsonProperty(value = "is_processed")
  private Integer isProcessed;
  @JsonProperty(value = "is_succeed")
  private Integer isSucceed;
  @JsonProperty(value = "result_info")
  private String resultInfo;

  public IdentityApplication() {
  }

  public IdentityApplication(String id,
                             String openId,
                             String applicantName,
                             String applicantPersonId,
                             String applicantPhone,
                             String additionalInfo,
                             Integer type,
                             Integer isProcessed,
                             Integer isSucceed,
                             String resultInfo) {
    this.id = id;
    this.openId = openId;
    this.applicantName = applicantName;
    this.applicantPersonId = applicantPersonId;
    this.applicantPhone = applicantPhone;
    this.additionalInfo = additionalInfo;
    this.type = type;
    this.isProcessed = isProcessed;
    this.isSucceed = isSucceed;
    this.resultInfo = resultInfo;
  }

  public Integer getIsProcessed() {
    return isProcessed;
  }

  public Integer getIsSucceed() {
    return isSucceed;
  }

  public Integer getType() {
    return type;
  }

  public String getAdditionalInfo() {
    return additionalInfo;
  }

  public String getApplicantName() {
    return applicantName;
  }

  public String getApplicantPersonId() {
    return applicantPersonId;
  }

  public String getApplicantPhone() {
    return applicantPhone;
  }

  public String getId() {
    return id;
  }

  public String getOpenId() {
    return openId;
  }

  public String getResultInfo() {
    return resultInfo;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setAdditionalInfo(String additionalInfo) {
    this.additionalInfo = additionalInfo;
  }

  public void setApplicantName(String applicantName) {
    this.applicantName = applicantName;
  }

  public void setApplicantPersonId(String applicantPersonId) {
    this.applicantPersonId = applicantPersonId;
  }

  public void setApplicantPhone(String applicantPhone) {
    this.applicantPhone = applicantPhone;
  }

  public void setIsProcessed(Integer isProcessed) {
    this.isProcessed = isProcessed;
  }

  public void setIsSucceed(Integer isSucceed) {
    this.isSucceed = isSucceed;
  }

  public void setOpenId(String openId) {
    this.openId = openId;
  }

  public void setResultInfo(String resultInfo) {
    this.resultInfo = resultInfo;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "IdentityApplication{" +
            "id='" + id + '\'' +
            ", openId='" + openId + '\'' +
            ", applicantName='" + applicantName + '\'' +
            ", applicantPersonId='" + applicantPersonId + '\'' +
            ", applicantPhone='" + applicantPhone + '\'' +
            ", additionalInfo='" + additionalInfo + '\'' +
            ", type=" + type +
            ", isProcessed=" + isProcessed +
            ", isSucceed=" + isSucceed +
            ", resultInfo='" + resultInfo + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    IdentityApplication that = (IdentityApplication) o;
    return Objects.equals(id, that.id) && Objects.equals(openId, that.openId)
            && Objects.equals(applicantName, that.applicantName) && Objects.equals(applicantPersonId, that.applicantPersonId)
            && Objects.equals(applicantPhone, that.applicantPhone) && Objects.equals(additionalInfo, that.additionalInfo)
            && Objects.equals(type, that.type) && Objects.equals(isProcessed, that.isProcessed)
            && Objects.equals(isSucceed, that.isSucceed) && Objects.equals(resultInfo, that.resultInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
            id, openId, applicantName, applicantPersonId, applicantPhone, additionalInfo, type, isProcessed, isSucceed, resultInfo);
  }
}
