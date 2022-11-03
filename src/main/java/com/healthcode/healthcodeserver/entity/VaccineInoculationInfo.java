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
@TableName("t_vaccine_inoculation_info")
public class VaccineInoculationInfo {
  private String personId;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Timestamp inoculationTime;
  private String inoculationFacility;
  private String vaccineName;
  private String inoculationNumber;
}
