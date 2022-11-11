package com.healthcode.healthcodeserver.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_user_relation")
public class UserRelation {
  @TableField("person_id_a")
  private String personIdA;
  @TableField("person_id_b")
  private String personIdB;
  /*
  0: 配偶
  1: 血亲
  2：姻亲
   */
  private Short relation;

  public UserRelation(String personIdA,
                      String personIdB,
                      Short relation) {
    this.personIdA = personIdA;
    this.personIdB = personIdB;
    this.relation = relation;
  }

  public UserRelation() {
  }

  public String getPersonIdA() {
    return personIdA;
  }

  public void setPersonIdA(String personIdA) {
    this.personIdA = personIdA;
  }

  public String getPersonIdB() {
    return personIdB;
  }

  public void setPersonIdB(String personIdB) {
    this.personIdB = personIdB;
  }

  public Short getRelation() {
    return relation;
  }

  public void setRelation(Short relation) {
    this.relation = relation;
  }

  @Override
  public String toString() {
    return "UserRelation{" +
            "personIdA='" + personIdA + '\'' +
            ", personIdB='" + personIdB + '\'' +
            ", relation=" + relation +
            '}';
  }
}
