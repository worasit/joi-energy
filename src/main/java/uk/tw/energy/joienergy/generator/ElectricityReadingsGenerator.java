package uk.tw.energy.joienergy.generator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import uk.tw.energy.joienergy.domain.ElectricityReading;

public class ElectricityReadingsGenerator {

  public List<ElectricityReading> generate(int number) throws NoSuchAlgorithmException {
    final Instant now = Instant.now();
    final SecureRandom secureRandom = SecureRandom.getInstanceStrong();
    return IntStream.range(0, number)
        .mapToObj(value -> buildElectricityReading(now, secureRandom, value))
        .sorted(Comparator.comparing(ElectricityReading::getTime))
        .collect(Collectors.toList());
  }

  protected ElectricityReading buildElectricityReading(
      Instant now,
      SecureRandom secureRandom,
      int value) {
    final double randomReading = Math.abs(secureRandom.nextGaussian());
    final BigDecimal readings =
        BigDecimal.valueOf(randomReading).setScale(4, RoundingMode.CEILING);
    return new ElectricityReading(now.minusSeconds(value * 10L), readings);
  }
}
