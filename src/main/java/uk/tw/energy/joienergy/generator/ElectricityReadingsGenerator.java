package uk.tw.energy.joienergy.generator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import uk.tw.energy.joienergy.domain.ElectricityReading;

public class ElectricityReadingsGenerator {
  public List<ElectricityReading> generate(int number) throws NoSuchAlgorithmException {
    final Instant now = Instant.now();
    final SecureRandom secureRandom = SecureRandom.getInstanceStrong();
    final List<ElectricityReading> generatedElectricityReadings =
        new java.util.ArrayList<>(Collections.emptyList());
    for (int i = 0; i < 5; i++) {
      final double randomReading = Math.abs(secureRandom.nextGaussian());
      final BigDecimal readings =
          BigDecimal.valueOf(randomReading).setScale(4, RoundingMode.CEILING);
      final ElectricityReading electricityReading =
          new ElectricityReading(now.minusSeconds(i * 10L), readings);

      generatedElectricityReadings.add(electricityReading);
    }
    generatedElectricityReadings.sort(Comparator.comparing(ElectricityReading::getTime));
    return generatedElectricityReadings;
  }
}
