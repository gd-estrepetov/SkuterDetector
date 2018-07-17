package pl.estrepetov.skuterdetector.service.distance;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pl.estrepetov.skuterdetector.model.Position;
import pl.estrepetov.skuterdetector.model.SkuterInfo;

import java.util.function.Function;

@Service
@Primary
public class GoogleDistanceCalculator implements DistanceCalculator {

  private final GeoApiContext geoApiContext;

  private Function<Position, LatLng> convertPosition = position -> new LatLng(position.getLat(), position.getLng());

  @Autowired
  public GoogleDistanceCalculator(GeoApiContext geoApiContext) {
    this.geoApiContext = geoApiContext;
  }

  @Override
  @Cacheable("distance_position")
  public Double calculateDistanceToSkuter(Position position, SkuterInfo skuterInfo) {
    DistanceMatrix distanceMatrix = DistanceMatrixApi.newRequest(geoApiContext)
                                                     .mode(TravelMode.WALKING)
                                                     .origins(convertPosition.apply(position))
                                                     .destinations(convertPosition.apply(skuterInfo.getPosition()))
                                                     .awaitIgnoreError();

    return (double) distanceMatrix.rows[0].elements[0].distance.inMeters;
  }

  @Override
  @Cacheable("distance_address")
  public Double calculateDistanceToSkuter(String address, SkuterInfo skuterInfo) {
    DistanceMatrix distanceMatrix = DistanceMatrixApi.newRequest(geoApiContext)
                                                     .mode(TravelMode.WALKING)
                                                     .origins(address)
                                                     .destinations(convertPosition.apply(skuterInfo.getPosition()))
                                                     .awaitIgnoreError();

    return (double) distanceMatrix.rows[0].elements[0].distance.inMeters;
  }
}
