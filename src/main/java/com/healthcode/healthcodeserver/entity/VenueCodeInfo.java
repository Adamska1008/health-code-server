package com.healthcode.healthcodeserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Objects;

@TableName("t_venue_code_info")
public class VenueCodeInfo {
  @TableId(value = "code_id", type = IdType.ASSIGN_ID)
  private String id;
  @TableField("venue_type")
  private String type;
  @TableField("venue_name")
  private String name;
  @TableField("venue_position")
  private String position;
  @TableField("venue_location")
  private String location;

  public VenueCodeInfo() {
  }

  public VenueCodeInfo(String id,
                       String type,
                       String name,
                       String position,
                       String location) {
    this.id = id;
    this.type = type;
    this.name = name;
    this.position = position;
    this.location = location;
  }

  public String getPosition() {
    return position;
  }

  public String getId() {
    return id;
  }

  public String getType() {
    return type;
  }

  public String getLocation() {
    return location;
  }

  public String getName() {
    return name;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "VenueCodeInfo{" +
            "id='" + id + '\'' +
            ", type='" + type + '\'' +
            ", name='" + name + '\'' +
            ", position='" + position + '\'' +
            ", location='" + location + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    VenueCodeInfo that = (VenueCodeInfo) o;
    return Objects.equals(id, that.id)
            && Objects.equals(type, that.type)
            && Objects.equals(name, that.name)
            && Objects.equals(position, that.position)
            && Objects.equals(location, that.location);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, name, position, location);
  }
}
