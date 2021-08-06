package uk.tw.energy.joienergy.controller;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.tw.energy.joienergy.domain.ElectricityReading;
import uk.tw.energy.joienergy.domain.MeterReadings;
import uk.tw.energy.joienergy.service.MeterReadingService;

@RestController
@RequestMapping("/readings")
public class MeterReadingController {

  private final MeterReadingService meterReadingService;

  public MeterReadingController(MeterReadingService meterReadingService) {
    this.meterReadingService = meterReadingService;
  }

  @PostMapping("/store")
  public ResponseEntity<Void> storeReadings(@RequestBody MeterReadings meterReadings) {
    if (!isMeterReadingsValid(meterReadings)) {
      return ResponseEntity.internalServerError().build();
    }
    this.meterReadingService
        .storeReading(meterReadings.getSmartMeterId(), meterReadings.getElectricityReadings());
    return ResponseEntity.ok().build();
  }

  private boolean isMeterReadingsValid(MeterReadings meterReadings) {
    String smartMeterId = meterReadings.getSmartMeterId();
    List<ElectricityReading> electricityReadings = meterReadings.getElectricityReadings();
    return smartMeterId != null
        && !smartMeterId.isEmpty()
        && electricityReadings != null
        && !electricityReadings.isEmpty();
  }

  @GetMapping("/read/{smartMeterId}")
  public ResponseEntity<List<ElectricityReading>> readReadings(@PathVariable String smartMeterId) {
    return this.meterReadingService
        .getReadings(smartMeterId)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  // TODO: Solution 1
//  @GetMapping("/read/{smartMeterId}")
//  public ResponseEntity readReadings(@PathVariable String smartMeterId) {
//    Optional<List<ElectricityReading>> readings = meterReadingService.getReadings(smartMeterId);
//    return readings.isPresent()
//        ? ResponseEntity.ok(readings.get().stream()
//        .collect(Collectors.minBy(Comparator.comparing(ElectricityReading::getReading))))
//        : ResponseEntity.notFound().build();
}
