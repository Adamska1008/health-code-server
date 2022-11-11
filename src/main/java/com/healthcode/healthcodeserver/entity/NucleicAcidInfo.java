package com.healthcode.healthcodeserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Objects;

@TableName("t_nucleic_acid_info")
public class NucleicAcidInfo {
  @TableId(type=IdType.ASSIGN_ID)
  private String nucleicAcidId;

  public String getNucleicAcidId() {
    return nucleicAcidId;
  }

  public void setNucleicAcidId(String nucleicAcidId) {
    this.nucleicAcidId = nucleicAcidId;
  }

  @Override
  public String toString() {
    return "NucleicAcidInfo{" +
            "nucleicAcidId='" + nucleicAcidId + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    NucleicAcidInfo that = (NucleicAcidInfo) o;
    return Objects.equals(nucleicAcidId, that.nucleicAcidId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nucleicAcidId);
  }
}
