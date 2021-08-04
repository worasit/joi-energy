package uk.tw.energy.joienergy.domain;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MeterReadings {
  private String smartMeterId;
  private List<ElectricityReading> electricityReadings;

  public MeterReadings(String smartMeterId, List<ElectricityReading> electricityReadings) {
    this.smartMeterId = smartMeterId;
    this.electricityReadings = electricityReadings;
  }
}
