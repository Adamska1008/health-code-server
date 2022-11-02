package com.healthcode.healthcodeserver.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("t_account")
public class Account {
  private String accountId;
  private String username;
  private String password;
  //category:0 用户，1 核酸检测人员，2 防疫管理人员
  private int category;
}
