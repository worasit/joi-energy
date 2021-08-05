package uk.tw.energy.joienergy.domain;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

public class PricePlan {

  private final String energySupplier;
  private final String planName;
  private final BigDecimal unitRate; // unit price per kWh
  private final List<PeakTimeMultiplier> peakTimeMultipliers;

  public PricePlan(
      String planName,
      String energySupplier,
      BigDecimal unitRate,
      List<PeakTimeMultiplier> peakTimeMultipliers) {
    this.energySupplier = energySupplier;
    this.planName = planName;
    this.unitRate = unitRate;
    this.peakTimeMultipliers = peakTimeMultipliers;
  }

  public String getEnergySupplier() {
    return energySupplier;
  }

  public String getPlanName() {
    return planName;
  }

  public BigDecimal getUnitRate() {
    return unitRate;
  }

  public List<PeakTimeMultiplier> getPeakTimeMultipliers() {
    return peakTimeMultipliers;
  }

  public BigDecimal getPrice(LocalDateTime localDate) {
    return this.peakTimeMultipliers.stream()
        .filter(peakTimeMultiplier -> peakTimeMultiplier.dayOfWeek.equals(localDate.getDayOfWeek()))
        .findFirst()
        .map(peakTimeMultiplier -> peakTimeMultiplier.multiplier.multiply(unitRate))
        .orElse(this.unitRate);
  }

  static class PeakTimeMultiplier {

    DayOfWeek dayOfWeek;
    BigDecimal multiplier;

    public PeakTimeMultiplier(DayOfWeek dayOfWeek, BigDecimal multiplier) {
      this.dayOfWeek = dayOfWeek;
      this.multiplier = multiplier;
    }
  }
}
