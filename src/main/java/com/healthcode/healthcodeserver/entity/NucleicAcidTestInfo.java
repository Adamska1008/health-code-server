package com.healthcode.healthcodeserver.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

@Data
@TableName("t_nucleic_acid_test_info")
public class NucleicAcidTestInfo {
  private String personId;
  private Date testTime;
  private String testInstitutionId;
  private String acidCode;
  /*
  0: 阴性
  1: 阳性
   */
  private Short testResult;
}
