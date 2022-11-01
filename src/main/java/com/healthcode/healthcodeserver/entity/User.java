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
}
