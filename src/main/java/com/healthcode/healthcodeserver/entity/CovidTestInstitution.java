package com.healthcode.healthcodeserver.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Objects;

@TableName("t_covid_test_institution")
public class CovidTestInstitution {
  @TableId
  private String institutionId;
  @TableField("institution_locate_area")
  private String location;
  @TableField("institution_name")
  private String name;

  public CovidTestInstitution() {
  }

  public CovidTestInstitution(String institutionId,
                              String location,
                              String name) {
    this.institutionId = institutionId;
    this.location = location;
    this.name = name;
  }

  public String getInstitutionId() {
    return institutionId;
  }

  public String getLocation() {
    return location;
  }

  public String getName() {
    return name;
  }

  public void setInstitutionId(String institutionId) {
    this.institutionId = institutionId;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "CovidTestInstitution{" +
            "institutionId='" + institutionId + '\'' +
            ", location='" + location + '\'' +
            ", name='" + name + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CovidTestInstitution that = (CovidTestInstitution) o;
    return Objects.equals(institutionId, that.institutionId) && Objects.equals(location, that.location) && Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(institutionId, location, name);
  }
}
