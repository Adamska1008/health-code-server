package com.healthcode.healthcodeserver.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.util.Objects;

@TableName("t_nucleic_acid_test_info")
public class NucleicAcidTestInfo {
  @JsonProperty("person_id")
  private String personId;
  @JsonProperty("test_time")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Timestamp testTime;
  @JsonProperty("test_institution_id")
  private String testInstitutionId;
  @JsonProperty("transfer_code")
  private String transferCode;
  @JsonProperty("test_result")
    /*
  0: 阴性
  1: 阳性
   */
  private Short testResult;



  public NucleicAcidTestInfo() {
  }

  public NucleicAcidTestInfo(String personId, Timestamp testTime,
                             String testInstitutionId, String transferCode) {
    this.personId = personId;
    this.testTime = testTime;
    this.testInstitutionId = testInstitutionId;
    this.transferCode = transferCode;
  }

  public String getTransferCode() {
    return transferCode;
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

  public void setTransferCode(String transferCode) {
    this.transferCode = transferCode;
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
            ", transferCode='" + transferCode + '\'' +
            ", testResult=" + testResult +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    NucleicAcidTestInfo that = (NucleicAcidTestInfo) o;
    return Objects.equals(personId, that.personId)
            && Objects.equals(testTime, that.testTime)
            && Objects.equals(testInstitutionId, that.testInstitutionId)
            && Objects.equals(transferCode, that.transferCode)
            && Objects.equals(testResult, that.testResult);
  }

  @Override
  public int hashCode() {
    return Objects.hash(personId, testTime, testInstitutionId, transferCode, testResult);
  }
}
