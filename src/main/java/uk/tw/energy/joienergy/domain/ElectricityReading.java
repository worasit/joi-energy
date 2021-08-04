package uk.tw.energy.joienergy.domain;

import java.math.BigDecimal;
import java.time.Instant;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class ElectricityReading {
  private Instant time;
  private BigDecimal reading; // kw
}
