package pl.estrepetov.skuterdetector.model;

import lombok.Data;
import pl.estrepetov.skuterdetector.model.SkuterInfo;

@Data
public class SkuterDistanceDto {
  private final SkuterInfo skuterInfo;
  private final Double distance;
}
