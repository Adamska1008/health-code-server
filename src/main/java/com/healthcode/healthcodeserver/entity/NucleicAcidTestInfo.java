package com.healthcode.healthcodeserver.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("t_nucleic_acid_test_info")
public class NucleicAcidTestInfo {
  private String personId;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Timestamp testTime;
  private String testInstitutionId;
  private String nucleicAcidId;
  /*
  0: 阴性
  1: 阳性
   */
  private Short testResult;
}
