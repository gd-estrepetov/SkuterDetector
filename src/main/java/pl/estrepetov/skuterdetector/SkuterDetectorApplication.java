package pl.estrepetov.skuterdetector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
@EnableCaching
public class SkuterDetectorApplication {

	public static void main(String[] args) {
		// this line to initialize bots context
		ApiContextInitializer.init();
		SpringApplication.run(SkuterDetectorApplication.class, args);
	}
}
