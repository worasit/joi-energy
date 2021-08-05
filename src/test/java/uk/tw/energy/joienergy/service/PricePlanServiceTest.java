package uk.tw.energy.joienergy.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.math.BigDecimal;
import java.util.List;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import uk.tw.energy.joienergy.domain.ElectricityReading;

@SpringBootTest
class PricePlanServiceTest {
  @Autowired
  private List pricePlans;
  @Autowired
  private PricePlanService pricePlanService;
  @Autowired
  private MeterReadingService meterReadingService;

  @Test
  void getConsumptionCostOfElectricityReadingsForEachPricePlan() {
//    // Arrange
//    final PricePlanService pricePlanService = new PricePlanService(new MeterReadingService());
//
//    // Act
//    final Optional<Map<String, BigDecimal>> pricePlans = pricePlanService
//        .getConsumptionCostOfElectricityReadingsForEachPricePlan("smart-meter-0");
//
//    // Assert
//    assertThat(pricePlans).isNotEmpty();
  }

//  @Test
//  void getAvgReading() {
//    // Arrange
//    final List<ElectricityReading> electricityReadings = meterReadingService
//        .getReadings("smart-meter-0").get();
//
//    // Act
//    final BigDecimal averageReadings = pricePlanService.getElectricityReadings(electricityReadings);
//
//    // Assert
//    assertThat(averageReadings).isCloseTo(BigDecimal.ONE, Percentage.withPercentage(1));
//  }
}