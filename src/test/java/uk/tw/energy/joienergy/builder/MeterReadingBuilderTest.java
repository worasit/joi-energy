package uk.tw.energy.joienergy.builder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.security.NoSuchAlgorithmException;
import org.junit.jupiter.api.Test;
import uk.tw.energy.joienergy.domain.MeterReadings;

class MeterReadingBuilderTest {

  @Test
  void build() throws NoSuchAlgorithmException {
    // Arrange
    final MeterReadingBuilder meterReadingBuilder = new MeterReadingBuilder();

    // Act
    final MeterReadings meterReadings = meterReadingBuilder
        .setSmartMeterId("smart-meter-0")
        .generateElectricityBuildings(3)
        .build();

    // Assert
    assertThat(meterReadings.getElectricityReadings().size()).isEqualTo(3);
  }
}