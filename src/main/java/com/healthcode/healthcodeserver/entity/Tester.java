package com.healthcode.healthcodeserver.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_tester")
public class Tester {
  private String openId;
}
