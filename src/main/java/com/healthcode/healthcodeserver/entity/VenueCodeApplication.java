package com.healthcode.healthcodeserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Objects;

@TableName("t_venue_code_application")
public class VenueCodeApplication {
  @TableId(value = "code_application_id", type = IdType.ASSIGN_ID)
  private String id;
  @TableField("code_application_person_name")
  private String applicantName;
  @TableField("code_application_person_id")
  private String applicantPersonId;
  @TableField("code_application_locate")
  private String location;
  @TableField("code_application_type")
  private String type;
  @TableField("code_application_name")
  private String placeName;
  /*
  0 未解决
  1 已解决
   */
  private Integer isSolved;
  /*
  0 已通过
  1 未通过
   */
  private Integer result;
  private String resultInfo;

  public VenueCodeApplication() {
  }

  public VenueCodeApplication(String id,
                              String applicantName,
                              String applicantPersonId,
                              String location,
                              String type,
                              String placeName,
                              Integer isSolved,
                              Integer result,
                              String resultInfo) {
    this.id = id;
    this.applicantName = applicantName;
    this.applicantPersonId = applicantPersonId;
    this.location = location;
    this.type = type;
    this.placeName = placeName;
    this.isSolved = isSolved;
    this.result = result;
    this.resultInfo = resultInfo;
  }

  public String getResultInfo() {
    return resultInfo;
  }

  public String getId() {
    return id;
  }

  public String getApplicantPersonId() {
    return applicantPersonId;
  }

  public String getApplicantName() {
    return applicantName;
  }

  public String getLocation() {
    return location;
  }

  public Integer getIsSolved() {
    return isSolved;
  }

  public Integer getResult() {
    return result;
  }

  public String getPlaceName() {
    return placeName;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setResultInfo(String resultInfo) {
    this.resultInfo = resultInfo;
  }

  public void setApplicantPersonId(String applicantPersonId) {
    this.applicantPersonId = applicantPersonId;
  }

  public void setApplicantName(String applicantName) {
    this.applicantName = applicantName;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public void setIsSolved(Integer isSolved) {
    this.isSolved = isSolved;
  }

  public void setPlaceName(String placeName) {
    this.placeName = placeName;
  }

  public void setResult(Integer result) {
    this.result = result;
  }

  @Override
  public String toString() {
    return "VenueCodeApplication{" +
            "id='" + id + '\'' +
            ", applicantName='" + applicantName + '\'' +
            ", applicantPersonId='" + applicantPersonId + '\'' +
            ", location='" + location + '\'' +
            ", type='" + type + '\'' +
            ", placeName='" + placeName + '\'' +
            ", isSolved=" + isSolved +
            ", result=" + result +
            ", resultInfo='" + resultInfo + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    VenueCodeApplication that = (VenueCodeApplication) o;
    return Objects.equals(id, that.id) && Objects.equals(applicantName, that.applicantName) && Objects.equals(applicantPersonId, that.applicantPersonId) && Objects.equals(location, that.location) && Objects.equals(type, that.type) && Objects.equals(placeName, that.placeName) && Objects.equals(isSolved, that.isSolved) && Objects.equals(result, that.result) && Objects.equals(resultInfo, that.resultInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, applicantName, applicantPersonId, location, type, placeName, isSolved, result, resultInfo);
  }
}
