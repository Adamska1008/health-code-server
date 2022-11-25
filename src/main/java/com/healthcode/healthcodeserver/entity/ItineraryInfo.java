package com.healthcode.healthcodeserver.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.sql.Timestamp;
import java.util.Objects;

@TableName("t_itinerary_information")
public class ItineraryInfo {
  private String personId;
  private String venueId;
  private Timestamp recordTime;

  public ItineraryInfo() {
  }

  public ItineraryInfo(String personId,
                       String venueId,
                       Timestamp recordTime) {
    this.personId = personId;
    this.venueId = venueId;
    this.recordTime = recordTime;
  }

  public String getPersonId() {
    return personId;
  }

  public Timestamp getRecordTime() {
    return recordTime;
  }

  public String getVenueId() {
    return venueId;
  }

  public void setPersonId(String personId) {
    this.personId = personId;
  }

  public void setRecordTime(Timestamp recordTime) {
    this.recordTime = recordTime;
  }

  public void setVenueId(String venueId) {
    this.venueId = venueId;
  }

  @Override
  public String toString() {
    return "ItineraryInfo{" +
            "personId='" + personId + '\'' +
            ", venueId='" + venueId + '\'' +
            ", recordTime='" + recordTime + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ItineraryInfo that = (ItineraryInfo) o;
    return Objects.equals(personId, that.personId)
            && Objects.equals(venueId, that.venueId)
            && Objects.equals(recordTime, that.recordTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(personId, venueId, recordTime);
  }
}
