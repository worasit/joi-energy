package uk.tw.energy.joienergy.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
public class Meter {
  @Id private String smartMeterId;
  @NotEmpty private String owner;
  @NotEmpty private String energySupplier;
}
