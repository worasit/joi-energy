package uk.tw.energy.joienergy.generator;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.junit.jupiter.api.Test;
import uk.tw.energy.joienergy.domain.ElectricityReading;

class ElectricityReadingsGeneratorTest {

  @Test
  void generate() throws NoSuchAlgorithmException {
    // Arrange
    final ElectricityReadingsGenerator generator = new ElectricityReadingsGenerator();
    final int expectedSize = 6;

    // Act

    final List<ElectricityReading> electricityReadings = generator.generate(expectedSize);

    // Assert
    assertThat(electricityReadings).hasSize(expectedSize);
  }
}
