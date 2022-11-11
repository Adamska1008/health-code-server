package com.healthcode.healthcodeserver.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@TableName("t_vaccine_inoculation_info")
public class VaccineInoculationInfo {
  @TableId
  private String personId;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Timestamp inoculationTime;
  private String inoculationFacility;
  private String vaccineName;
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
