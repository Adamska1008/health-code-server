package com.healthcode.healthcodeserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Objects;

@TableName("t_tester")
public class Tester {
  @TableId(type = IdType.ASSIGN_ID)
  private String openId;
  private String personId;
  private String phone;
  private String name;

  public Tester() {
  }

  public Tester(String openId,
                String personId,
                String phone,
                String name) {
    this.openId = openId;
    this.personId = personId;
    this.phone = phone;
    this.name = name;
  }

  public String getPersonId() {
    return personId;
  }

  public String getOpenId() {
    return openId;
  }

  public String getName() {
    return name;
  }

  public String getPhone() {
    return phone;
  }

  public void setPersonId(String personId) {
    this.personId = personId;
  }

  public void setOpenId(String openId) {
    this.openId = openId;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  @Override
  public String toString() {
    return "Tester{" +
            "openId='" + openId + '\'' +
            ", personId='" + personId + '\'' +
            ", phone='" + phone + '\'' +
            ", name='" + name + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Tester tester = (Tester) o;
    return Objects.equals(openId, tester.openId)
            && Objects.equals(personId, tester.personId)
            && Objects.equals(phone, tester.phone)
            && Objects.equals(name, tester.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(openId, personId, phone, name);
  }
}
