package pl.estrepetov.skuterdetector.repository;

import pl.estrepetov.skuterdetector.model.SkuterInfo;

import java.util.List;

public interface SkuterRepository {

  List<SkuterInfo> getAllSkuters();
}
