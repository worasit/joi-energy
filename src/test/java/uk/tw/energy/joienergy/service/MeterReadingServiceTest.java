package uk.tw.energy.joienergy.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.assertj.core.util.Maps;
import org.junit.jupiter.api.Test;
import uk.tw.energy.joienergy.domain.ElectricityReading;

class MeterReadingServiceTest {

  @Test
  void getReadings_whenSpecifyValidMeterId_shouldReturnSpecificElectricReadings() {
    // Arrange
    final Map<String, List<ElectricityReading>> prebuiltMeterReadings =
        Maps.newHashMap(
            "smart-meter-0",
            Collections.singletonList(new ElectricityReading(Instant.now(), BigDecimal.TEN)));
    final MeterReadingService meterReadingService = new MeterReadingService(prebuiltMeterReadings);

    // Act
    final Optional<List<ElectricityReading>> readings =
        meterReadingService.getReadings("smart-meter-0");

    // Assert
    assertThat(readings).isPresent();
    assertThat(readings.get().get(0).getReading()).isEqualTo(BigDecimal.TEN);
  }

  @Test
  void getReadings_whenSpecifyInValidMeterId_shouldReturnEmpty() {
    // Arrange
    final Map<String, List<ElectricityReading>> prebuiltMeterReadings =
        Maps.newHashMap(
            "smart-meter-0",
            Collections.singletonList(new ElectricityReading(Instant.now(), BigDecimal.TEN)));
    final MeterReadingService meterReadingService = new MeterReadingService(prebuiltMeterReadings);

    // Act
    final Optional<List<ElectricityReading>> readings =
        meterReadingService.getReadings("smart-meter-invalid");

    // Assert
    assertThat(readings).isNotPresent();
  }
}
