package uk.tw.energy.joienergy.controller;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import uk.tw.energy.joienergy.JoiEnergyApplication;
import uk.tw.energy.joienergy.domain.EnergySuppliers;
import uk.tw.energy.joienergy.dto.MeterDto;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = JoiEnergyApplication.class)
class MeterControllerTest {

  @Autowired private TestRestTemplate testRestTemplate;

  @Test
  void getAllMeters() {
    // Arrange

    // Act
    final ResponseEntity<String> responseEntity =
        testRestTemplate.getForEntity("/meters", String.class);

    // Assert
    assertThat(responseEntity.getStatusCode().is2xxSuccessful()).isTrue();
  }

  @Test
  void getSpecificMeter() {
    // Arrange
    final MeterDto meterDto =
        new MeterDto("smart-meter-7", "sarah", EnergySuppliers.DR_EVIL_DARK_ENERGY);
    final ResponseEntity<MeterDto> createResponse =
        testRestTemplate.postForEntity("/meters", meterDto, MeterDto.class);

    // Act
    final ResponseEntity<String> responseEntity =
        testRestTemplate.getForEntity("/meters/smart-meter-7", String.class);

    // Assert
    assertThat(responseEntity.getStatusCode().is2xxSuccessful()).isTrue();
  }
}
