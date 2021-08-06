package uk.tw.energy.joienergy.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import uk.tw.energy.joienergy.domain.ElectricityReading;
import uk.tw.energy.joienergy.domain.PricePlan;
import uk.tw.energy.joienergy.exception.MeterNotFoundException;

@Service
public class PricePlanService {

  private final List<PricePlan> pricePlans;
  private final MeterReadingService meterReadingService;


  public PricePlanService(List<PricePlan> pricePlans, MeterReadingService meterReadingService) {
    this.pricePlans = pricePlans;
    this.meterReadingService = meterReadingService;
  }

  public void createNewPricePlanTariff(PricePlan pricePlan) {
    this.pricePlans.add(pricePlan);
  }

  public Optional<List<PricePlan>> getAllPricePlanTariff() {
    return Optional.ofNullable(this.pricePlans);
  }

  public Optional<Map<String, BigDecimal>> getConsumptionCostOfElectricityReadingsForEachPricePlan(
      String smartMeterId) {
    final List<ElectricityReading> electricityReadings = getElectricityReadings(smartMeterId);
    final BigDecimal avgReadings = calculateAvergeReadings(electricityReadings);
    final BigDecimal timeElapsedInHours = calculateTimeElapsedInHours(electricityReadings);
    final BigDecimal kwH = avgReadings.divide(timeElapsedInHours, RoundingMode.HALF_UP);
    return Optional.of(pricePlans.stream().collect(Collectors
        .toMap(PricePlan::getPlanName, pricePlan -> kwH.multiply(pricePlan.getUnitRate()))));
  }

  protected List<ElectricityReading> getElectricityReadings(String smartMeterId) {
    return this.meterReadingService
        .getReadings(smartMeterId)
        .orElseThrow(() -> new MeterNotFoundException(smartMeterId));
  }

  private BigDecimal calculateTimeElapsedInHours(List<ElectricityReading> electricityReadings) {
    final Instant startedDuration = electricityReadings.stream().map(ElectricityReading::getTime)
        .min(Instant::compareTo).orElse(Instant.EPOCH);
    final Instant endedDuration = electricityReadings.stream().map(ElectricityReading::getTime)
        .max(Instant::compareTo).orElse(Instant.EPOCH);
    return BigDecimal
        .valueOf(Duration.between(startedDuration, endedDuration).toSeconds() / 3600.0);
  }

  private BigDecimal calculateAvergeReadings(List<ElectricityReading> electricityReadings) {
    return electricityReadings.stream().map(ElectricityReading::getReading)
        .reduce(BigDecimal.ZERO, BigDecimal::add)
        .divide(BigDecimal.valueOf(electricityReadings.size()), RoundingMode.HALF_UP);
  }
}
