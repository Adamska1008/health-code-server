package com.healthcode.healthcodeserver.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("t_vaccine_inoculation_info")
public class VaccineInoculationInfo {
  private String personId;
  private Timestamp inoculationTime;
  private String inoculationFacility;
  private String vaccineName;
  private String inoculationNumber;
}
