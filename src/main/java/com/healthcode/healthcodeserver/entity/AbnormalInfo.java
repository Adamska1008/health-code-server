package com.healthcode.healthcodeserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("t_abnormal_info_appeal_investigation")
public class AbnormalInfo {
  @TableId(value = "appeal_id", type = IdType.ASSIGN_ID)
  private String id;

}
