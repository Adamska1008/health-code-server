package com.healthcode.healthcodeserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@TableName("t_abnormal_info_application")
public class AbnormalInfo {
  @TableId(value = "application_id", type = IdType.ASSIGN_ID)
  @JsonProperty(value = "application_id")
  private String id;
  @JsonProperty(value = "person_name")
  private String personName;
  @TableField("person_phone")
  @JsonProperty("phone_number")
  private String phoneNumber;
  @TableField("additional_information")
  @JsonProperty("additional_info")
  private String additionalInfo;
  @JsonProperty("type")
  private String type;
  @JsonProperty("is_investigated")
  private Short isInvestigated;
  @JsonProperty("is_processed")
  private Short isProcessed;

  public AbnormalInfo() {
  }

  public AbnormalInfo(String id,
                      String personName,
                      String phoneNumber,
                      String additionalInfo,
                      String type,
                      Short isInvestigated,
                      Short isProcessed) {
  }

  public String getPersonName() {
    return personName;
  }

  public String getAdditionalInfo() {
    return additionalInfo;
  }

  public String getId() {
    return id;
  }

  public String getType() {
    return type;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public Short getIsInvestigated() {
    return isInvestigated;
  }

  public Short getIsProcessed() {
    return isProcessed;
  }

  public void setPersonName(String personName) {
    this.personName = personName;
  }

  public void setAdditionalInfo(String additionalInfo) {
    this.additionalInfo = additionalInfo;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setIsProcessed(Short isProcessed) {
    this.isProcessed = isProcessed;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public void setIsInvestigated(Short isInvestigated) {
    this.isInvestigated = isInvestigated;
  }

  @Override
  public String toString() {
    return "AbnormalInfo{" +
            "id='" + id + '\'' +
            ", personName='" + personName + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", additionalInfo='" + additionalInfo + '\'' +
            ", type='" + type + '\'' +
            ", isInvestigated=" + isInvestigated +
            ", isProcessed=" + isProcessed +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AbnormalInfo that = (AbnormalInfo) o;
    return Objects.equals(id, that.id)
            && Objects.equals(personName, that.personName)
            && Objects.equals(phoneNumber, that.phoneNumber)
            && Objects.equals(additionalInfo, that.additionalInfo)
            && Objects.equals(type, that.type)
            && Objects.equals(isInvestigated, that.isInvestigated)
            && Objects.equals(isProcessed, that.isProcessed);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, personName, phoneNumber,
            additionalInfo, type, isInvestigated, isProcessed);
  }
}
