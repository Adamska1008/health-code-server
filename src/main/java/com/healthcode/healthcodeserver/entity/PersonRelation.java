package com.healthcode.healthcodeserver.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("t_person_relation")
public class PersonRelation {
  @TableField("person_id_a")
  private String personIdA;
  @TableField("person_id_b")
  private String personIdB;
  /*
  0: 配偶
  1: 血亲
  2：姻亲
   */
  @TableField("relation")
  private Short relation;
}
