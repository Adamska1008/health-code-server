package com.healthcode.healthcodeserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("t_venue_code_info")
public class VenueCodeInfo {
  @TableId(value = "code_id", type = IdType.ASSIGN_ID)
  private String id;
  private String venueType;
  private String venueName;
  private String venueLocation;

  public String getId() {
    return id;
  }

  public String getVenueName() {
    return venueName;
  }

  public String getVenueType() {
    return venueType;
  }

  public String getVenueLocation() {
    return venueLocation;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setVenueName(String venueName) {
    this.venueName = venueName;
  }

  public void setVenueType(String venueType) {
    this.venueType = venueType;
  }

  public void setVenueLocation(String venueLocation) {
    this.venueLocation = venueLocation;
  }
}
