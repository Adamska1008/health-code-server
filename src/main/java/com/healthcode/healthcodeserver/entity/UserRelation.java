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
}
