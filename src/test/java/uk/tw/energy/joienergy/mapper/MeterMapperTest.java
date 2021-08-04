package uk.tw.energy.joienergy.mapper;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import org.junit.jupiter.api.Test;
import uk.tw.energy.joienergy.domain.EnergySuppliers;
import uk.tw.energy.joienergy.domain.Meter;
import uk.tw.energy.joienergy.dto.MeterDto;

class MeterMapperTest {

  @Test
  void dtoToEntity() {
    // Arrange
    final String expectedOwner = "Sarah";
    final String expectedSmartMeterId = "smart-meter-0";
    final String expectedSupplier = EnergySuppliers.DR_EVIL_DARK_ENERGY;
    final MeterDto sarahMeter =
        MeterDto.builder()
            .smartMeterId(expectedSmartMeterId)
            .energySupplier(expectedSupplier)
            .owner(expectedOwner)
            .build();

    // Act
    final Meter meter = MeterMapper.INSTANCE.dtoToEntity(sarahMeter);

    // Assert
    assertThat(meter.getEnergySupplier()).isEqualTo(expectedSupplier);
    assertThat(meter.getOwner()).isEqualTo(expectedOwner);
    assertThat(meter.getSmartMeterId()).isEqualTo(expectedSmartMeterId);
  }

  @Test
  void entityToDto() {
    // Arrange
    final String expectedOwner = "Sarah";
    final String expectedSmartMeterId = "smart-meter-0";
    final String expectedSupplier = EnergySuppliers.DR_EVIL_DARK_ENERGY;
    final Meter sarahMeter =
        Meter.builder()
            .smartMeterId(expectedSmartMeterId)
            .energySupplier(expectedSupplier)
            .owner(expectedOwner)
            .build();

    // Act
    final MeterDto meter = MeterMapper.INSTANCE.entityToDto(sarahMeter);

    // Assert
    assertThat(meter.getEnergySupplier()).isEqualTo(expectedSupplier);
    assertThat(meter.getOwner()).isEqualTo(expectedOwner);
    assertThat(meter.getSmartMeterId()).isEqualTo(expectedSmartMeterId);
  }
}
