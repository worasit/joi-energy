package uk.tw.energy.joienergy.repository;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import uk.tw.energy.joienergy.domain.EnergySuppliers;
import uk.tw.energy.joienergy.domain.Meter;

@DataJpaTest
class MeterRepositoryTest {

  @Qualifier("meterRepository")
  @Autowired
  private MeterRepository meterRepository;

  @Test
  void saveMeter() {
    // Arrange
    final Meter sarahMeter =
        Meter.builder()
            .smartMeterId("smart-meter-0")
            .owner("sarah")
            .energySupplier(EnergySuppliers.DR_EVIL_DARK_ENERGY)
            .build();

    // Act
    meterRepository.save(sarahMeter);

    // Assert
    final Meter actualMeter = meterRepository.getById("smart-meter-0");
    assertThat(actualMeter.getOwner()).isEqualTo("sarah");
    assertThat(actualMeter.getEnergySupplier()).isEqualTo(EnergySuppliers.DR_EVIL_DARK_ENERGY);
  }

  @Test
  void getMeter() {
    // Arrange
    final Meter sarahMeter =
        Meter.builder()
            .smartMeterId("smart-meter-0")
            .owner("sarah")
            .energySupplier(EnergySuppliers.DR_EVIL_DARK_ENERGY)
            .build();

    // Act
    meterRepository.save(sarahMeter);

    // Assert
    final Meter actualMeter = meterRepository.getById("smart-meter-0");
    assertThat(actualMeter.getOwner()).isEqualTo("sarah");
    assertThat(actualMeter.getEnergySupplier()).isEqualTo(EnergySuppliers.DR_EVIL_DARK_ENERGY);
  }

  @Test
  void deleteMeter() {
    // Arrange
    final Meter sarahMeter =
        Meter.builder()
            .smartMeterId("smart-meter-0")
            .owner("sarah")
            .energySupplier(EnergySuppliers.DR_EVIL_DARK_ENERGY)
            .build();

    final Meter johnMeter =
        Meter.builder()
            .smartMeterId("smart-meter-1")
            .owner("john")
            .energySupplier(EnergySuppliers.DR_EVIL_DARK_ENERGY)
            .build();

    meterRepository.save(sarahMeter);
    meterRepository.save(johnMeter);

    // Act
    meterRepository.delete(sarahMeter);

    // Assert
    final String smartMeterId = sarahMeter.getSmartMeterId();
    Assertions.assertThrows(
        JpaObjectRetrievalFailureException.class, () -> meterRepository.getById(smartMeterId));
    assertThat(meterRepository.findById(johnMeter.getSmartMeterId())).isPresent();
  }
}
