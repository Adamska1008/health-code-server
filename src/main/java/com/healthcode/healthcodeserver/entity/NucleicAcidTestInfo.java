package com.healthcode.healthcodeserver.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("t_nucleic_acid_test_info")
public class NucleicAcidTestInfo {
  private String personId;
  private Date testTime;
  private String testInstitutionId;
  private String nucleicAcidId;
  /*
  0: 阴性
  1: 阳性
   */
  private Short testResult;
}
