package pl.estrepetov.skuterdetector.service.distance;

import org.springframework.stereotype.Service;
import pl.estrepetov.skuterdetector.model.Position;
import pl.estrepetov.skuterdetector.model.SkuterInfo;

import static java.lang.Math.*;

@Service
public class SimpleDistanceCalculator implements DistanceCalculator {

  private static double PI_RAD = Math.PI / 180.0;

  @Override public Double calculateDistanceToSkuter(Position position, SkuterInfo skuterInfo) {
    return distanceInMeters(position, skuterInfo.getPosition());
  }

  @Override public Double calculateDistanceToSkuter(String address, SkuterInfo skuterInfo) {
    throw new UnsupportedOperationException("Calculating distance by address is not supported");
  }

  private double distanceInMeters(Position first, Position other) {
    return greatCircleInKilometers(first.getLat(), first.getLng(), other.getLat(), other.getLng()) * 1000;
  }

  private double greatCircleInKilometers(double lat1, double long1, double lat2, double long2) {
    double phi1 = lat1 * PI_RAD;
    double phi2 = lat2 * PI_RAD;
    double lam1 = long1 * PI_RAD;
    double lam2 = long2 * PI_RAD;

    return 6371.01 * acos(sin(phi1) * sin(phi2) + cos(phi1) * cos(phi2) * cos(lam2 - lam1));
  }

}
