package pl.estrepetov.skuterdetector.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import pl.estrepetov.skuterdetector.model.Position;

@Getter
@ToString
@EqualsAndHashCode
public class SkuterInfo {
  private final Integer id;
  private final String name;
  private final String plate;
  private final Position position;

  private final Double range;

  public SkuterInfo(@JsonProperty("id") Integer id,
                    @JsonProperty("name") String name,
                    @JsonProperty("plate") String plate,
                    @JsonProperty("position") Position position,
                    @JsonProperty("range") Double range) {
    this.id = id;
    this.name = name;
    this.plate = plate;
    this.position = position;
    this.range = range;
  }
}