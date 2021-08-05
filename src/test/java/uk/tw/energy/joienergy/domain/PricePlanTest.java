package uk.tw.energy.joienergy.domain;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collections;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

class PricePlanTest {

  @Test
  void shouldReturnTheEnergySupplierGivenInTheConstructor() {
    // Arrange

    // Act
    final String expectedPricePlanId = "price-plan-0";
    final PricePlan pricePlan = new PricePlan(
        expectedPricePlanId,
        EnergySuppliers.DR_EVIL_DARK_ENERGY,
        BigDecimal.TEN,
        Collections.emptyList());

    // Assert
    assertThat(pricePlan.getEnergySupplier()).isEqualTo(EnergySuppliers.DR_EVIL_DARK_ENERGY);
    assertThat(pricePlan.getPlanName()).isEqualTo(expectedPricePlanId);
  }

  @Test
  void shouldReturnTheMultiplierPriceGivenAnPeakDateTime() {
    // Arrange
    LocalDateTime normalDateTime = LocalDateTime.of(2021, Month.AUGUST, 5, 12, 0, 0);
    PricePlan.PeakTimeMultiplier peakTimeMultiplier =
        new PricePlan.PeakTimeMultiplier(DayOfWeek.THURSDAY, BigDecimal.TEN);
    PricePlan pricePlan =
        new PricePlan(null, null, BigDecimal.ONE, singletonList(peakTimeMultiplier));
    // Act
    BigDecimal price = pricePlan.getPrice(normalDateTime);

    // Assert
    assertThat(price).isCloseTo(BigDecimal.TEN, Percentage.withPercentage(1));
  }

  @Test
  void shouldReturnTheNormalUnitRatePriceGivenAnOrdinaryDateTime() {
    // Arrange
    LocalDateTime normalDateTime = LocalDateTime.of(2021, Month.AUGUST, 5, 12, 0, 0);
    PricePlan.PeakTimeMultiplier peakTimeMultiplier =
        new PricePlan.PeakTimeMultiplier(DayOfWeek.WEDNESDAY, BigDecimal.TEN);
    PricePlan pricePlan =
        new PricePlan(null, null, BigDecimal.ONE, singletonList(peakTimeMultiplier));
    // Act
    BigDecimal price = pricePlan.getPrice(normalDateTime);

    // Assert
    assertThat(price).isCloseTo(BigDecimal.ONE, Percentage.withPercentage(1));
  }
}