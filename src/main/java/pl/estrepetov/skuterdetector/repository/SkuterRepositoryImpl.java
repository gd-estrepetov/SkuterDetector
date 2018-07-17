package pl.estrepetov.skuterdetector.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import pl.estrepetov.skuterdetector.config.BlinkeeProperties;
import pl.estrepetov.skuterdetector.model.BlinkeeResponse;
import pl.estrepetov.skuterdetector.model.SkuterInfo;

import java.util.List;

@Repository
public class SkuterRepositoryImpl implements SkuterRepository {

  private final BlinkeeProperties blinkeeProperties;

  @Autowired public SkuterRepositoryImpl(BlinkeeProperties blinkeeProperties) {
    this.blinkeeProperties = blinkeeProperties;
  }

  @Override
  public List<SkuterInfo> getAllSkuters() {
    RestTemplate restTemplate = new RestTemplate();

    ResponseEntity<BlinkeeResponse> rateResponse =
        restTemplate.exchange(blinkeeProperties.getSkutersUrl(),
                              HttpMethod.GET, null,
                              new ParameterizedTypeReference<BlinkeeResponse>() {
                              });
    return rateResponse.getBody().getData();
  }

}
