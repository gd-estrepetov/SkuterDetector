package pl.estrepetov.skuterdetector.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.estrepetov.skuterdetector.model.Position;
import pl.estrepetov.skuterdetector.model.SkuterDistanceDto;
import pl.estrepetov.skuterdetector.model.SkuterInfo;
import pl.estrepetov.skuterdetector.repository.SkuterRepository;
import pl.estrepetov.skuterdetector.service.distance.DistanceCalculator;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Stream;

@Service
public class SkuterManager {

  private final SkuterRepository skuterRepository;

  private final DistanceCalculator distanceCalculator;

  @Autowired
  public SkuterManager(SkuterRepository skuterRepository,
                       DistanceCalculator distanceCalculator) {
    this.skuterRepository = skuterRepository;
    this.distanceCalculator = distanceCalculator;
  }

  public SkuterDistanceDto findClosestSkuterInfo(Position position) {

    return findClosestSkuterInfo(
        si -> new SkuterDistanceDto(si, distanceCalculator.calculateDistanceToSkuter(position, si)));
  }

  public SkuterDistanceDto findClosestSkuterInfo(String address) {
    return findClosestSkuterInfo(
        si -> new SkuterDistanceDto(si, distanceCalculator.calculateDistanceToSkuter(address, si)));
  }

  private SkuterDistanceDto findClosestSkuterInfo(Function<SkuterInfo, SkuterDistanceDto> distanceDtoFunction) {
    List<SkuterInfo> allSkuters = skuterRepository.getAllSkuters();

    Stream<CompletableFuture<SkuterDistanceDto>> completableFutureStream = allSkuters
        .stream()
        .filter(skuterInfo -> skuterInfo.getRange() > 0)
        .map(skuterInfo -> CompletableFuture.supplyAsync(() -> distanceDtoFunction.apply(skuterInfo)));

    CompletableFuture<SkuterDistanceDto>[] cfs = completableFutureStream.toArray(CompletableFuture[]::new);
    CompletableFuture.allOf(cfs);

    return Stream.of(cfs)
                 .map(CompletableFuture::join)
                 .min(Comparator.comparing(SkuterDistanceDto::getDistance))
                 .get();
  }
}
