package pl.estrepetov.skuterdetector.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class Position {

  private final Double lat;
  private final Double lng;

  public Position(@JsonProperty("lat") Double lat,
                  @JsonProperty("lng") Double lng) {
    this.lat = lat;
    this.lng = lng;
  }

}
