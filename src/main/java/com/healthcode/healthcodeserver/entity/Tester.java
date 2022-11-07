package com.healthcode.healthcodeserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_tester")
public class Tester {
  @TableId(type = IdType.ASSIGN_ID)
  private String openId;
  private String personId;
  private String phone;
  private String name;
}
