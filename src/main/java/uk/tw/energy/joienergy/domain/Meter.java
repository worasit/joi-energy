package uk.tw.energy.joienergy.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Meter {
  @Id private String smartMeterId;
  @NotEmpty private String owner;
  @NotEmpty private String energySupplier;
}
