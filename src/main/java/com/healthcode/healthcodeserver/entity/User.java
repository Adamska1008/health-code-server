package com.healthcode.healthcodeserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_user_info")
public class User {
  @TableId(value="person_id", type=IdType.NONE)
  private String id;
  private String name;
  private String phoneNumber;
  // 0: male; 1: female
  private Short gender;
  private String healthCodeColor;
}
