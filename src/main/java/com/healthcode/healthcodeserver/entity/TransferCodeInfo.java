package com.healthcode.healthcodeserver.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.sql.Timestamp;

@TableName("t_transfer_code_info")
public class TransferCodeInfo {
  private String transferCode;
  private String testerOpenId;
  private String testTime;
  private int personNumber;
  private short isTransferred;

  public TransferCodeInfo(String transferCode,
                          String testerOpenId,
                          String testTime,
                          int personNumber,
                          short isTransferred) {
    this.transferCode = transferCode;
    this.testerOpenId = testerOpenId;
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

  public String getTesterOpenId() {
    return testerOpenId;
  }

  public void setTesterOpenId(String tester_open_id) {
    this.testerOpenId = tester_open_id;
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
            ", tester_open_id='" + testerOpenId + '\'' +
            ", testTime=" + testTime +
            ", personNumber=" + personNumber +
            ", isTransferred=" + isTransferred +
            '}';
  }
}
