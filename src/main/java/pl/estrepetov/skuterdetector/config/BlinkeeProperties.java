package pl.estrepetov.skuterdetector.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("blinkee")
public class BlinkeeProperties {
  private String skutersUrl;

  public String getSkutersUrl() {
    return skutersUrl;
  }

  public void setSkutersUrl(String skutersUrl) {
    this.skutersUrl = skutersUrl;
  }
}
