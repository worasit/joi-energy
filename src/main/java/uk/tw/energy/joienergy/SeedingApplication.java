package uk.tw.energy.joienergy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.tw.energy.joienergy.domain.EnergySuppliers;
import uk.tw.energy.joienergy.domain.Meter;
import uk.tw.energy.joienergy.repository.MeterRepository;

@Configuration
@Slf4j
public class SeedingApplication {

  private final MeterRepository meterRepository;

  public SeedingApplication(@Qualifier("meterRepository") MeterRepository meterRepository) {
    this.meterRepository = meterRepository;
  }

  @Bean
  CommandLineRunner prebuiltMeters() {
    return args -> {
      log.info(buildMeter("smart-meter-0", "Sarah", EnergySuppliers.DR_EVIL_DARK_ENERGY));
      log.info(buildMeter("smart-meter-1", "Peter", EnergySuppliers.THE_GREEN_ECO));
      log.info(buildMeter("smart-meter-2", "Charlie", EnergySuppliers.DR_EVIL_DARK_ENERGY));
      log.info(buildMeter("smart-meter-3", "Andrea", EnergySuppliers.POWER_FOR_EVERYONE));
      log.info(buildMeter("smart-meter-4", "Alex", EnergySuppliers.THE_GREEN_ECO));
    };
  }

  private String buildMeter(String meterId, String owner, String energySupplier) {
    return "Upload meter: " + meterRepository.save(new Meter(meterId, owner, energySupplier));
  }
}
