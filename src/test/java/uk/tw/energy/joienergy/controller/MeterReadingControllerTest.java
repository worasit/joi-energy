package uk.tw.energy.joienergy.controller;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.tw.energy.joienergy.domain.ElectricityReading;
import uk.tw.energy.joienergy.service.MeterReadingService;

class MeterReadingControllerTest {

  @Test
  void givenMeterIdThatIsNotRecognised_ShouldReturnNotFound() {
    // Arrange
    final HashMap<String, List<ElectricityReading>> meterAssocaitedReadings = new HashMap<>();
    final MeterReadingController meterReadingController =
        new MeterReadingController(new MeterReadingService(meterAssocaitedReadings));

    // Act
    final ResponseEntity<List<ElectricityReading>> electricityReadings =
        meterReadingController.readReadings("smart-meter-0");

    // Assert
    assertThat(electricityReadings.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
  }

  @Test
  void givenMeterIdIsValid_shouldReturnSuccessResponse() {
    // Arrange
    final HashMap<String, List<ElectricityReading>> meterAssocaitedReadings = new HashMap<>();
    final String expectedMeterId = "smart-meter-0";
    meterAssocaitedReadings.put(expectedMeterId, new ArrayList<>());

    final MeterReadingService meterReadingService =
        new MeterReadingService(meterAssocaitedReadings);
    final MeterReadingController controller = new MeterReadingController(meterReadingService);

    // Act
    final ResponseEntity<List<ElectricityReading>> responseEntity =
        controller.readReadings(expectedMeterId);

    // Assert
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
  }
}
