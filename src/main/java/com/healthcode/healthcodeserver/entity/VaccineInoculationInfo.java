package com.healthcode.healthcodeserver.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

@Data
@TableName("t_vaccine_inoculation_info")
public class VaccineInoculationInfo {
  private String personId;
  private Date inoculationTime;
  private String inoculationFacility;
  private String vaccineName;
  private String inoculationNumber;
}
