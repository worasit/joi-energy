package uk.tw.energy.joienergy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import uk.tw.energy.joienergy.domain.ElectricityReading;
import uk.tw.energy.joienergy.domain.EnergySuppliers;
import uk.tw.energy.joienergy.domain.Meter;
import uk.tw.energy.joienergy.generator.ElectricityReadingsGenerator;
import uk.tw.energy.joienergy.repository.MeterRepository;

@Configuration
@Slf4j
public class SeedingApplication {

  private static final String MOST_EVIL_PRICE_PLAN_ID = "price-plan-0";
  private static final String RENEWABLES_PRICE_PLAN_ID = "price-plan-1";
  private static final String STANDARD_PRICE_PLAN_ID = "price-plan-2";

  private final MeterRepository meterRepository;

  public SeedingApplication(@Qualifier("meterRepository") MeterRepository meterRepository) {
    this.meterRepository = meterRepository;
  }

  @Bean
  public Map<String, List<ElectricityReading>> perMeterElectricityReadings() {
    final Map<String, List<ElectricityReading>> readings = new HashMap<>();
    final ElectricityReadingsGenerator electricityReadingsGenerator =
        new ElectricityReadingsGenerator();
    smartMeterToPricePlanAccounts()
        .keySet()
        .forEach(
            smartMeterId -> {
              try {
                readings.put(smartMeterId, electricityReadingsGenerator.generate(20));
              } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
              }
            });
    return readings;
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

  @Bean
  public Map<String, String> smartMeterToPricePlanAccounts() {
    final Map<String, String> smartMeterToPricePlanAccounts = new HashMap<>();
    smartMeterToPricePlanAccounts.put("smart-meter-0", MOST_EVIL_PRICE_PLAN_ID);
    smartMeterToPricePlanAccounts.put("smart-meter-1", RENEWABLES_PRICE_PLAN_ID);
    smartMeterToPricePlanAccounts.put("smart-meter-2", MOST_EVIL_PRICE_PLAN_ID);
    smartMeterToPricePlanAccounts.put("smart-meter-3", STANDARD_PRICE_PLAN_ID);
    smartMeterToPricePlanAccounts.put("smart-meter-4", RENEWABLES_PRICE_PLAN_ID);
    return smartMeterToPricePlanAccounts;
  }

  @Bean
  @Primary
  public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
    ObjectMapper objectMapper = builder.createXmlMapper(false).build();
    objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    return objectMapper;
  }

  private String buildMeter(String meterId, String owner, String energySupplier) {
    return "Upload meter: " + meterRepository.save(new Meter(meterId, owner, energySupplier));
  }
}
