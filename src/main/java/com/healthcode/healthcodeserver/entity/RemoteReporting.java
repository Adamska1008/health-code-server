package com.healthcode.healthcodeserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@TableName("t_remote_reporting")
public class RemoteReporting {
  @TableId(value = "report_id", type = IdType.ASSIGN_ID)
  @JsonProperty("report_id")
  private String id;
  @JsonProperty(value = "person_name")
  private String personName;
  @JsonProperty(value = "person_id")
  private String personId;
  @JsonProperty(value = "img_url")
  private String imgUrl;
  @TableField("_from")
  private String from;
  @TableField("_to")
  private String to;
  @JsonProperty("additional_info")
  private String additionalInfo;
  @JsonProperty("is_checked")
  private Short isChecked;
  @JsonProperty("is_allowed")
  private Short isAllowed;

  public RemoteReporting() {
  }

  public RemoteReporting(String id,
                         String personName,
                         String personId,
                         String imgUrl,
                         String from,
                         String to,
                         String additionalInfo,
                         Short isChecked,
                         Short isAllowed) {
    this.id = id;
    this.personName = personName;
    this.personId = personId;
    this.imgUrl = imgUrl;
    this.from = from;
    this.to = to;
    this.additionalInfo = additionalInfo;
    this.isAllowed = isAllowed;
    this.isChecked = isChecked;
  }

  public String getPersonId() {
    return personId;
  }

  public String getId() {
    return id;
  }

  public String getAdditionalInfo() {
    return additionalInfo;
  }

  public String getFrom() {
    return from;
  }

  public String getImgUrl() {
    return imgUrl;
  }

  public String getPersonName() {
    return personName;
  }

  public String getTo() {
    return to;
  }

  public Short getIsAllowed() {
    return isAllowed;
  }

  public Short getIsChecked() {
    return isChecked;
  }

  public void setPersonId(String personId) {
    this.personId = personId;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setAdditionalInfo(String additionalInfo) {
    this.additionalInfo = additionalInfo;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
  }

  public void setPersonName(String personName) {
    this.personName = personName;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public void setIsAllowed(Short isAllowed) {
    this.isAllowed = isAllowed;
  }

  public void setIsChecked(Short isChecked) {
    this.isChecked = isChecked;
  }

  @Override
  public String toString() {
    return "RemoteReporting{" +
            "id='" + id + '\'' +
            ", personName='" + personName + '\'' +
            ", personId='" + personId + '\'' +
            ", imgUrl='" + imgUrl + '\'' +
            ", from='" + from + '\'' +
            ", to='" + to + '\'' +
            ", additionalInfo='" + additionalInfo + '\'' +
            ", isChecked=" + isChecked +
            ", isAllowed=" + isAllowed +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RemoteReporting reporting = (RemoteReporting) o;
    return Objects.equals(id, reporting.id) && Objects.equals(personName, reporting.personName) && Objects.equals(personId, reporting.personId) && Objects.equals(imgUrl, reporting.imgUrl) && Objects.equals(from, reporting.from) && Objects.equals(to, reporting.to) && Objects.equals(additionalInfo, reporting.additionalInfo) && Objects.equals(isChecked, reporting.isChecked) && Objects.equals(isAllowed, reporting.isAllowed);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, personName, personId, imgUrl, from, to, additionalInfo, isChecked, isAllowed);
  }
}

