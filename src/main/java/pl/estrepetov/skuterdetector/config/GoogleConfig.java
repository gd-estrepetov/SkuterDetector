package pl.estrepetov.skuterdetector.config;

import com.google.maps.GeoApiContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GoogleConfig {

  @Bean
  public GeoApiContext geoApiContext(GoogleProperties googleProperties) {

    return new GeoApiContext.Builder()
        .apiKey(googleProperties.getApiKey())
        .build();
  }
}
