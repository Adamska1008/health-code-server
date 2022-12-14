package com.healthcode.healthcodeserver.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Objects;

@TableName("t_bind_family_application")
public class FamilyBingApplication {
  @TableId(value = "application_id", type = IdType.ASSIGN_ID)
  @JsonProperty("application_id")
  private String id;
  @JsonProperty("applicant_name")
  private String applicantName;
  @JsonProperty("applicant_person_id")
  private String applicantPersonId;
  @JsonProperty("relative_name")
  private String relativeName;
  @JsonProperty("relative_person_id")
  private String relativePersonId;
  @TableField("additional_information")
  @JsonProperty("additional_information")
  private String additionalInfo;
  @JsonProperty("relation_type")
  private Integer relationType;
  @JsonProperty("is_processed")
  private Integer isProcessed;
  @JsonProperty("is_succeed")
  private Integer isSucceed;
  @JsonProperty("result_info")
  private String resultInfo;

  public FamilyBingApplication() {
  }

  public FamilyBingApplication(String id,
                               String applicantName,
                               String applicantPersonId,
                               String relativeName,
                               String relativePersonId,
                               String additionalInfo,
                               Integer relationType,
                               Integer isProcessed,
                               Integer isSucceed,
                               String resultInfo) {
    this.id = id;
    this.applicantName = applicantName;
    this.applicantPersonId = applicantPersonId;
    this.relativeName = relativeName;
    this.relativePersonId = relativePersonId;
    this.additionalInfo = additionalInfo;
    this.relationType = relationType;
    this.isProcessed = isProcessed;
    this.isSucceed = isSucceed;
    this.resultInfo = resultInfo;
  }

  public String getApplicantName() {
    return applicantName;
  }

  public String getId() {
    return id;
  }

  public String getApplicantPersonId() {
    return applicantPersonId;
  }

  public String getResultInfo() {
    return resultInfo;
  }

  public String getAdditionalInfo() {
    return additionalInfo;
  }

  public Integer getIsSucceed() {
    return isSucceed;
  }

  public Integer getIsProcessed() {
    return isProcessed;
  }

  public Integer getRelationType() {
    return relationType;
  }

  public String getRelativeName() {
    return relativeName;
  }

  public String getRelativePersonId() {
    return relativePersonId;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setApplicantPersonId(String applicantPersonId) {
    this.applicantPersonId = applicantPersonId;
  }

  public void setApplicantName(String applicantName) {
    this.applicantName = applicantName;
  }

  public void setResultInfo(String resultInfo) {
    this.resultInfo = resultInfo;
  }

  public void setIsSucceed(Integer isSucceed) {
    this.isSucceed = isSucceed;
  }

  public void setIsProcessed(Integer isProcessed) {
    this.isProcessed = isProcessed;
  }

  public void setAdditionalInfo(String additionalInfo) {
    this.additionalInfo = additionalInfo;
  }

  public void setRelationType(Integer relationType) {
    this.relationType = relationType;
  }

  public void setRelativeName(String relativeName) {
    this.relativeName = relativeName;
  }

  public void setRelativePersonId(String relativePersonId) {
    this.relativePersonId = relativePersonId;
  }

  @Override
  public String toString() {
    return "FamilyBingApplication{" +
            "id='" + id + '\'' +
            ", applicantName='" + applicantName + '\'' +
            ", relativeName='" + relativeName + '\'' +
            ", relativePersonId='" + relativePersonId + '\'' +
            ", additionalInfo='" + additionalInfo + '\'' +
            ", relationType=" + relationType +
            ", isProcessed=" + isProcessed +
            ", isSucceed=" + isSucceed +
            ", resultInfo='" + resultInfo + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    FamilyBingApplication that = (FamilyBingApplication) o;
    return Objects.equals(id, that.id)
            && Objects.equals(applicantName, that.applicantName)
            && Objects.equals(relativeName, that.relativeName)
            && Objects.equals(relativePersonId, that.relativePersonId)
            && Objects.equals(additionalInfo, that.additionalInfo)
            && Objects.equals(relationType, that.relationType)
            && Objects.equals(isProcessed, that.isProcessed)
            && Objects.equals(isSucceed, that.isSucceed)
            && Objects.equals(resultInfo, that.resultInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, applicantName, relativeName,
            relativePersonId, additionalInfo, relationType,
            isProcessed, isSucceed, resultInfo);
  }
}
