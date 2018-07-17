package pl.estrepetov.skuterdetector.telegram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import pl.estrepetov.skuterdetector.config.TelegramProperties;

@Component
public class SkuterDetectorBot extends TelegramLongPollingBot {

  @Autowired
  private TelegramProperties telegramProperties;

  @Override public void onUpdateReceived(Update update) {

    SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                                            .setChatId(update.getMessage().getChatId())
                                            .setText(update.getMessage().getText());
    try {
      execute(message); // Call method to send the message
    } catch (TelegramApiException e) {
      e.printStackTrace();
    }
  }

  @Override public String getBotUsername() {
    return telegramProperties.getBotUsername();
  }

  @Override public String getBotToken() {
    return telegramProperties.getBotToken();
  }
}
