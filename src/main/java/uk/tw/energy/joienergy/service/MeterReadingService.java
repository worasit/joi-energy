package uk.tw.energy.joienergy.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Service;
import uk.tw.energy.joienergy.domain.ElectricityReading;

@Service
public class MeterReadingService {
  private final Map<String, List<ElectricityReading>> meterAssocaitedReadings;

  public MeterReadingService(Map<String, List<ElectricityReading>> meterAssocaitedReadings) {
    this.meterAssocaitedReadings = meterAssocaitedReadings;
  }

  public Optional<List<ElectricityReading>> getReadings(String smartMeterId) {
    return Optional.ofNullable(this.meterAssocaitedReadings.get(smartMeterId));
  }
}
