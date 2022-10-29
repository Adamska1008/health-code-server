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
  private String phoneNumber;
  private Short gender; // 0: male; 1: female
  private String healthCodeColor;
}
