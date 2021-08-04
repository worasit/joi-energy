package uk.tw.energy.joienergy.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.tw.energy.joienergy.domain.ElectricityReading;
import uk.tw.energy.joienergy.service.MeterReadingService;

@RestController
@RequestMapping("/readings")
public class MeterReadingController {

  private final MeterReadingService meterReadingService;

  public MeterReadingController(MeterReadingService meterReadingService) {
    this.meterReadingService = meterReadingService;
  }

  @GetMapping("/read/{smartMeterId}")
  public ResponseEntity<List<ElectricityReading>> readReadings(@PathVariable String smartMeterId) {
    return this.meterReadingService
        .getReadings(smartMeterId)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }
}
