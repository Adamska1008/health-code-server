package com.healthcode.healthcodeserver.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.sql.Timestamp;

@TableName("t_transfer_code_info")
public class TransferCodeInfo {
  private String transferCode;
  private String tester_open_id;
  private String testTime;
  private int personNumber;
  private short isTransferred;

  public TransferCodeInfo(String transferCode,
                          String tester_open_id,
                          String testTime,
                          int personNumber,
                          short isTransferred) {
    this.transferCode = transferCode;
    this.tester_open_id = tester_open_id;
    this.testTime = testTime;
    this.personNumber = personNumber;
    this.isTransferred = isTransferred;
  }

  public TransferCodeInfo() {
  }

  public String getTransferCode() {
    return transferCode;
  }

  public void setTransferCode(String transferCode) {
    this.transferCode = transferCode;
  }

  public String getTester_open_id() {
    return tester_open_id;
  }

  public void setTester_open_id(String tester_open_id) {
    this.tester_open_id = tester_open_id;
  }

  public String getTestTime() {
    return testTime;
  }

  public void setTestTime(String testTime) {
    this.testTime = testTime;
  }

  public int getPersonNumber() {
    return personNumber;
  }

  public void setPersonNumber(int personNumber) {
    this.personNumber = personNumber;
  }

  public short getIsTransferred() {
    return isTransferred;
  }

  public void setIsTransferred(short isTransferred) {
    this.isTransferred = isTransferred;
  }

  @Override
  public String toString() {
    return "TransferCodeInfo{" +
            "transferCode='" + transferCode + '\'' +
            ", tester_open_id='" + tester_open_id + '\'' +
            ", testTime=" + testTime +
            ", personNumber=" + personNumber +
            ", isTransferred=" + isTransferred +
            '}';
  }
}
