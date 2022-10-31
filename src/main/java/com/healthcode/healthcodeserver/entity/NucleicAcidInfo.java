package com.healthcode.healthcodeserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("t_nucleic_acid_info")
@Data
public class NucleicAcidInfo {
  @TableId(type=IdType.ASSIGN_ID)
  private String nucleicAcidId;
}
