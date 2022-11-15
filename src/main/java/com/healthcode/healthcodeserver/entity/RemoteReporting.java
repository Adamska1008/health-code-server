package com.healthcode.healthcodeserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Objects;

@TableName("t_remote_reporting")
public class RemoteReporting {
  @TableId(value = "report_id", type = IdType.ASSIGN_ID)
  private String id;
  private String personName;
  private String personId;
  private String imgUrl;
  @TableField("_from")
  private String from;
  @TableField("_to")
  private String to;
  private String additionalInfo;

  public RemoteReporting() {
  }

  public RemoteReporting(String id,
                         String personName,
                         String personId,
                         String imgUrl,
                         String from,
                         String to,
                         String additionalInfo) {
    this.id = id;
    this.personName = personName;
    this.personId = personId;
    this.imgUrl = imgUrl;
    this.from = from;
    this.to = to;
    this.additionalInfo = additionalInfo;
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
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RemoteReporting that = (RemoteReporting) o;
    return Objects.equals(id, that.id) && Objects.equals(personName, that.personName) && Objects.equals(personId, that.personId) && Objects.equals(imgUrl, that.imgUrl) && Objects.equals(from, that.from) && Objects.equals(to, that.to) && Objects.equals(additionalInfo, that.additionalInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, personName, personId, imgUrl, from, to, additionalInfo);
  }
}

