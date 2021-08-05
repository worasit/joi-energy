package uk.tw.energy.joienergy.builder;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import uk.tw.energy.joienergy.domain.ElectricityReading;
import uk.tw.energy.joienergy.domain.MeterReadings;
import uk.tw.energy.joienergy.generator.ElectricityReadingsGenerator;

public class MeterReadingBuilder {

  private static final String DEFAULT_METER_ID = "id";

  private String smartMeterId = DEFAULT_METER_ID;
  private List<ElectricityReading> electricityReadings;

  public MeterReadingBuilder setSmartMeterId(String smartMeterId) {
    this.smartMeterId = smartMeterId;
    return this;
  }

  public MeterReadingBuilder generateElectricityBuildings() throws NoSuchAlgorithmException {
    return generateElectricityBuildings(5);
  }

  public MeterReadingBuilder generateElectricityBuildings(int number)
      throws NoSuchAlgorithmException {
    final ElectricityReadingsGenerator electricityReadingsGenerator = new ElectricityReadingsGenerator();
    this.electricityReadings = electricityReadingsGenerator.generate(number);
    return this;
  }

  public MeterReadings build() {
    return new MeterReadings(this.smartMeterId, this.electricityReadings);
  }
}
