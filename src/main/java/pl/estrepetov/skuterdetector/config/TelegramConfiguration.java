package pl.estrepetov.skuterdetector.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import pl.estrepetov.skuterdetector.telegram.SkuterDetectorBot;

@Configuration
public class TelegramConfiguration {

  @Bean
  public TelegramBotsApi telegramBotsApi(SkuterDetectorBot skuterDetectorBot) {
    TelegramBotsApi botsApi = new TelegramBotsApi();

    try {
      botsApi.registerBot(skuterDetectorBot);
    } catch (TelegramApiException e) {
      e.printStackTrace();
    }
    return botsApi;
  }
}
