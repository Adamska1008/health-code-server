package com.healthcode.healthcodeserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@TableName("t_identity_application")
@Data
public class IdentityApplication {
  @TableId(value = "application_id", type = IdType.ASSIGN_ID)
  private String id;
  private String openId;
  private String applicantName;
  private String applicantPersonId;
  private String applicantPhone;
  @TableField(value = "additional_information")
  private String additionalInfo;
  // 0:核酸检测人员 1: 政务管理人员
  @TableField("apply_type")
  private Integer type;
  private Integer isProcessed;
  private Integer isSucceed;
  private String resultInfo;
}
