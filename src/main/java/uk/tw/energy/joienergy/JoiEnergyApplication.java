package uk.tw.energy.joienergy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class JoiEnergyApplication {

  public static void main(String[] args) {
    SpringApplication.run(JoiEnergyApplication.class, args);
    log.info("Application is successfully started \uD83D\uDE80");
  }
}
