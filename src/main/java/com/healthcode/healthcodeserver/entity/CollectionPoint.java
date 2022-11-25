package com.healthcode.healthcodeserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@TableName("t_collection_point_information")
public class CollectionPoint {
  @TableId(value = "collection_point_id", type = IdType.ASSIGN_ID)
  @JsonProperty(value = "collection_point_id")
  private String id;
  @TableField(value = "collection_point_position")
  @JsonProperty(value = "collection_point_position")
  private String position;
  @TableField(value = "collection_point_institution")
  @JsonProperty(value = "collection_point_institution")
  private String institution;
  @TableField(value = "collection_point_principal")
  @JsonProperty(value = "collection_point_principal")
  private String principal;
  @TableField(value = "collection_point_contact_phone")
  @JsonProperty(value = "collection_point_contact_phone")
  private String phone;

  public CollectionPoint() {
  }

  public CollectionPoint(String id,
                         String position,
                         String institution,
                         String principal,
                         String phone) {
    this.id = id;
    this.position = position;
    this.institution = institution;
    this.principal = principal;
    this.phone = phone;
  }

  public String getId() {
    return id;
  }

  public String getPhone() {
    return phone;
  }

  public String getInstitution() {
    return institution;
  }

  public String getPosition() {
    return position;
  }

  public String getPrincipal() {
    return principal;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public void setInstitution(String institution) {
    this.institution = institution;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public void setPrincipal(String principal) {
    this.principal = principal;
  }

  @Override
  public String toString() {
    return "CollectionPoint{" +
            "id='" + id + '\'' +
            ", position='" + position + '\'' +
            ", institution='" + institution + '\'' +
            ", principal='" + principal + '\'' +
            ", phone='" + phone + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CollectionPoint that = (CollectionPoint) o;
    return Objects.equals(id, that.id)
            && Objects.equals(position, that.position)
            && Objects.equals(institution, that.institution)
            && Objects.equals(principal, that.principal)
            && Objects.equals(phone, that.phone);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, position, institution, principal, phone);
  }
}
