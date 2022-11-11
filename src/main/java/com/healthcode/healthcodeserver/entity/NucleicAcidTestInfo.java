package com.healthcode.healthcodeserver.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.util.Objects;

@TableName("t_nucleic_acid_test_info")
public class NucleicAcidTestInfo {
  private String personId;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Timestamp testTime;
  private String testInstitutionId;
  private String nucleicAcidId;
  /*
  0: 阴性
  1: 阳性
   */
  private Short testResult;

  public NucleicAcidTestInfo() {
  }

  public NucleicAcidTestInfo(String personId, Timestamp testTime, String testInstitutionId, String nucleicAcidId) {
    this.personId = personId;
    this.testTime = testTime;
    this.testInstitutionId = testInstitutionId;
    this.nucleicAcidId = nucleicAcidId;
  }

  public String getNucleicAcidId() {
    return nucleicAcidId;
  }

  public Short getTestResult() {
    return testResult;
  }

  public String getPersonId() {
    return personId;
  }

  public String getTestInstitutionId() {
    return testInstitutionId;
  }

  public Timestamp getTestTime() {
    return testTime;
  }

  public void setNucleicAcidId(String nucleicAcidId) {
    this.nucleicAcidId = nucleicAcidId;
  }

  public void setPersonId(String personId) {
    this.personId = personId;
  }

  public void setTestInstitutionId(String testInstitutionId) {
    this.testInstitutionId = testInstitutionId;
  }

  public void setTestResult(Short testResult) {
    this.testResult = testResult;
  }

  public void setTestTime(Timestamp testTime) {
    this.testTime = testTime;
  }

  @Override
  public String toString() {
    return "NucleicAcidTestInfo{" +
            "personId='" + personId + '\'' +
            ", testTime=" + testTime +
            ", testInstitutionId='" + testInstitutionId + '\'' +
            ", nucleicAcidId='" + nucleicAcidId + '\'' +
            ", testResult=" + testResult +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    NucleicAcidTestInfo that = (NucleicAcidTestInfo) o;
    return Objects.equals(personId, that.personId) && Objects.equals(testTime, that.testTime) && Objects.equals(testInstitutionId, that.testInstitutionId) && Objects.equals(nucleicAcidId, that.nucleicAcidId) && Objects.equals(testResult, that.testResult);
  }

  @Override
  public int hashCode() {
    return Objects.hash(personId, testTime, testInstitutionId, nucleicAcidId, testResult);
  }
}
