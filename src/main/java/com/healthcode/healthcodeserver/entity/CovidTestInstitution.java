package com.healthcode.healthcodeserver.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("t_covid_test_institution")
@Data
public class CovidTestInstitution {
  @TableId
  private String institutionId;
  @TableField("institution_locate_area")
  private String location;
  @TableField("institution_name")
  private String name;
}
