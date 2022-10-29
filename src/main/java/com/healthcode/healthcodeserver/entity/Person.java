package com.healthcode.healthcodeserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_person_info")
public class Person {
  @TableId(value="person_id", type=IdType.NONE)
  private String id;
  @TableField(value="person_name")
  private String name;
  @TableField(value="phone_number")
  private String phoneNumber;
  @TableField(value="gender")
  private Short gender; // 0: male; 1: female
  @TableField(value="health_code_color")
  private String healthCodeColor;
}
