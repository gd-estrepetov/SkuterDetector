package pl.estrepetov.skuterdetector.service.distance;

import pl.estrepetov.skuterdetector.model.Position;
import pl.estrepetov.skuterdetector.model.SkuterInfo;

public interface DistanceCalculator {

  Double calculateDistanceToSkuter(Position position, SkuterInfo skuterInfo);

  Double calculateDistanceToSkuter(String address, SkuterInfo skuterInfo);
}
