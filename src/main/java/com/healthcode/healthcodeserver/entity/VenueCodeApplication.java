package com.healthcode.healthcodeserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@TableName("t_venue_code_application")
public class VenueCodeApplication {
  @TableId(value = "application_id", type = IdType.ASSIGN_ID)
  @JsonProperty("application_id")
  private String id;
  @JsonProperty("applicant_name")
  private String applicantName;
  @JsonProperty("applicant_person_id")
  private String applicantPersonId;
  private String position;
  private String location;
  private String type;
  @JsonProperty("place_name")
  private String placeName;
  @JsonProperty("is_solved")
  private Integer isSolved;
  @JsonProperty("is_passed")
  private Integer isPassed;
  @JsonProperty("result_info")
  private String resultInfo;
  @JsonProperty("venue_id")
  private String venueId;

  public VenueCodeApplication() {
  }

  public VenueCodeApplication(String id,
                              String applicantName,
                              String applicantPersonId,
                              String position,
                              String location,
                              String type,
                              String placeName,
                              Integer isSolved,
                              Integer isPassed,
                              String resultInfo,
                              String venueId) {
    this.id = id;
    this.applicantName = applicantName;
    this.applicantPersonId = applicantPersonId;
    this.position = position;
    this.location = location;
    this.type = type;
    this.placeName = placeName;
    this.isSolved = isSolved;
    this.isPassed = isPassed;
    this.resultInfo = resultInfo;
    this.venueId = venueId;
  }

  public String getVenueId() {
    return venueId;
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

  public String getPosition() {
    return position;
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

  public Integer getIsPassed() {
    return isPassed;
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

  public void setIsPassed(Integer isPassed) {
    this.isPassed = isPassed;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public void setVenueId(String venueId) {
    this.venueId = venueId;
  }

  @Override
  public String toString() {
    return "VenueCodeApplication{" +
            "id='" + id + '\'' +
            ", applicantName='" + applicantName + '\'' +
            ", applicantPersonId='" + applicantPersonId + '\'' +
            ", position='" + position + '\'' +
            ", location='" + location + '\'' +
            ", type='" + type + '\'' +
            ", placeName='" + placeName + '\'' +
            ", isSolved=" + isSolved +
            ", isPassed=" + isPassed +
            ", resultInfo='" + resultInfo + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    VenueCodeApplication that = (VenueCodeApplication) o;
    return Objects.equals(id, that.id)
            && Objects.equals(applicantName, that.applicantName)
            && Objects.equals(applicantPersonId, that.applicantPersonId)
            && Objects.equals(position, that.position)
            && Objects.equals(location, that.location)
            && Objects.equals(type, that.type)
            && Objects.equals(placeName, that.placeName)
            && Objects.equals(isSolved, that.isSolved)
            && Objects.equals(isPassed, that.isPassed)
            && Objects.equals(resultInfo, that.resultInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, applicantName, applicantPersonId, position,
            location, type, placeName, isSolved, isPassed, resultInfo);
  }
}
