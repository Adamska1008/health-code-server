package com.healthcode.healthcodeserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.util.Objects;

@TableName("t_abnormal_info_application")
public class AbnormalInfo {
  @TableId(value = "application_id", type = IdType.ASSIGN_ID)
  @JsonProperty("application_id")
  private String id;
  @JsonProperty("person_name")
  private String personName;
  @JsonProperty("person_id")
  private String personId;
  @TableField("person_phone")
  @JsonProperty("phone_number")
  private String phoneNumber;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @JsonProperty("submit_time")
  private Timestamp submitTime;
  @TableField("additional_information")
  @JsonProperty("additional_info")
  private String additionalInfo;
  /**
   * 0 健康码
   * 1 行程码
   * 2 核酸检测
   * 3 疫苗接种
   */
  private Integer type;
  @JsonProperty("is_investigated")
  private Short isInvestigated;
  @JsonProperty("is_processed")
  private Short isProcessed;

  public AbnormalInfo() {
  }

  public AbnormalInfo(String id,
                      String personName,
                      String personId,
                      String phoneNumber,
                      String additionalInfo,
                      Integer type,
                      Short isInvestigated,
                      Short isProcessed) {
    this.id = id;
    this.personName = personName;
    this.personId = personId;
    this.phoneNumber = phoneNumber;
    this.submitTime = new Timestamp(System.currentTimeMillis());
    this.additionalInfo = additionalInfo;
    this.type = type;
    this.isInvestigated = isInvestigated;
    this.isProcessed = isProcessed;
  }

  public String getPersonId() {
    return personId;
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

  public Integer getType() {
    return type;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public Timestamp getSubmitTime() {
    return submitTime;
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

  public void setPersonId(String personId) {
    this.personId = personId;
  }

  public void setSubmitTime(Timestamp submitTime) {
    this.submitTime = submitTime;
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

  public void setType(Integer type) {
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
