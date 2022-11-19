package com.healthcode.healthcodeserver.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

@TableName("t_vaccine_inoculation_info")
public class VaccineInoculationInfo {
  @JsonProperty("person_id")
  @TableId
  private String personId;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @JsonProperty("inoculation_time")
  private Timestamp inoculationTime;
  @JsonProperty("inoculation_facility")
  private String inoculationFacility;
  @JsonProperty("vaccine_name")
  private String vaccineName;
  @JsonProperty("inoculation_number")
  private String inoculationNumber;


  public VaccineInoculationInfo(String personId,
                                Timestamp inoculationTime,
                                String inoculationFacility,
                                String vaccineName,
                                String inoculationNumber) {
    this.personId = personId;
    this.inoculationTime = inoculationTime;
    this.inoculationFacility = inoculationFacility;
    this.vaccineName = vaccineName;
    this.inoculationNumber = inoculationNumber;
  }

  public VaccineInoculationInfo() {
  }


  public String getPersonId() {
    return personId;
  }

  public void setPersonId(String personId) {
    this.personId = personId;
  }

  public Timestamp getInoculationTime() {
    return inoculationTime;
  }

  public void setInoculationTime(Timestamp inoculationTime) {
    this.inoculationTime = inoculationTime;
  }

  public String getInoculationFacility() {
    return inoculationFacility;
  }

  public void setInoculationFacility(String inoculationFacility) {
    this.inoculationFacility = inoculationFacility;
  }

  public String getVaccineName() {
    return vaccineName;
  }

  public void setVaccineName(String vaccineName) {
    this.vaccineName = vaccineName;
  }

  public String getInoculationNumber() {
    return inoculationNumber;
  }

  public void setInoculationNumber(String inoculationNumber) {
    this.inoculationNumber = inoculationNumber;
  }


  @Override
  public String toString() {
    return "VaccineInoculationInfo{" +
            "personId='" + personId + '\'' +
            ", inoculationTime=" + inoculationTime +
            ", inoculationFacility='" + inoculationFacility + '\'' +
            ", vaccineName='" + vaccineName + '\'' +
            ", inoculationNumber='" + inoculationNumber + '\'' +
            '}';
  }

}
