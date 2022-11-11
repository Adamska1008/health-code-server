package com.healthcode.healthcodeserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_user_info")
public class User {
  @TableId(type=IdType.NONE)
  private String personId;
  @TableField(value = "person_name")
  private String name;
  private String phoneNumber;
  @TableField(value = "wx_openid")
  private String openId;
  // 0: 男; 1: 女
  private Short gender;
  // health_code_color 0:绿 1:黄 2:绿
  private String healthCodeColor;

  public User(String personId,
              String name,
              String phoneNumber,
              String openId,
              Short gender,
              String healthCodeColor) {
    this.personId = personId;
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.openId = openId;
    this.gender = gender;
    this.healthCodeColor = healthCodeColor;
  }

  public User() {
  }

  public String getPersonId() {
    return personId;
  }

  public void setPersonId(String personId) {
    this.personId = personId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getOpenId() {
    return openId;
  }

  public void setOpenId(String openId) {
    this.openId = openId;
  }

  public Short getGender() {
    return gender;
  }

  public void setGender(Short gender) {
    this.gender = gender;
  }

  public String getHealthCodeColor() {
    return healthCodeColor;
  }

  public void setHealthCodeColor(String healthCodeColor) {
    this.healthCodeColor = healthCodeColor;
  }

  @Override
  public String toString() {
    return "User{" +
            "personId='" + personId + '\'' +
            ", name='" + name + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", openId='" + openId + '\'' +
            ", gender=" + gender +
            ", healthCodeColor='" + healthCodeColor + '\'' +
            '}';
  }
}
