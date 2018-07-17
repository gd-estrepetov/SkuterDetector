package pl.estrepetov.skuterdetector.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("telegram")
public class TelegramProperties {

  private String botToken;

  private String botUsername;

  public String getBotToken() {
    return botToken;
  }

  public void setBotToken(String botToken) {
    this.botToken = botToken;
  }

  public String getBotUsername() {
    return botUsername;
  }

  public void setBotUsername(String botUsername) {
    this.botUsername = botUsername;
  }
}
