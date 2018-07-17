package pl.estrepetov.skuterdetector.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.estrepetov.skuterdetector.model.Position;
import pl.estrepetov.skuterdetector.model.SkuterDistanceDto;
import pl.estrepetov.skuterdetector.model.SkuterInfo;
import pl.estrepetov.skuterdetector.repository.SkuterRepository;
import pl.estrepetov.skuterdetector.service.SkuterManager;

import java.util.List;

@RestController
public class SkuterController {

  private final SkuterRepository skuterRepository;

  private final SkuterManager skuterManager;

  @Autowired
  public SkuterController(SkuterRepository skuterRepository,
                          SkuterManager skuterManager) {
    this.skuterRepository = skuterRepository;
    this.skuterManager = skuterManager;
  }

  @GetMapping("skuters")
  public List<SkuterInfo> getAllSkuters() {
    return skuterRepository.getAllSkuters();
  }

  @PostMapping("skuters/closest/position")
  public SkuterDistanceDto findClosest(@RequestBody Position position) {
    return skuterManager.findClosestSkuterInfo(position);
  }

  @PostMapping("skuters/closest/address")
  public SkuterDistanceDto findClosest(@RequestBody String address) {
    return skuterManager.findClosestSkuterInfo(address);
  }
}
