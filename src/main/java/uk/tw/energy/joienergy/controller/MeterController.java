package uk.tw.energy.joienergy.controller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.tw.energy.joienergy.domain.Meter;
import uk.tw.energy.joienergy.dto.MeterDto;
import uk.tw.energy.joienergy.exception.MeterDuplicatedException;
import uk.tw.energy.joienergy.exception.MeterNotFoundException;
import uk.tw.energy.joienergy.mapper.MeterMapper;
import uk.tw.energy.joienergy.repository.MeterRepository;

@RestController
@RequestMapping("/meters")
public class MeterController {

  private final MeterRepository meterRepository;
  private final MeterMapper meterMapper;

  public MeterController(
      @Qualifier("meterRepository") MeterRepository meterRepository, MeterMapper meterMapper) {
    this.meterRepository = meterRepository;
    this.meterMapper = meterMapper;
  }

  @GetMapping
  public ResponseEntity<List<MeterDto>> getAllMeters() {
    final List<MeterDto> meterDtos =
        this.meterRepository.findAll().stream()
            .map(meterMapper::entityToDto)
            .sorted(Comparator.comparing(MeterDto::getSmartMeterId))
            .collect(Collectors.toList());
    return ResponseEntity.ok(meterDtos);
  }

  @GetMapping("/{smartMeterId}")
  public ResponseEntity<MeterDto> getMeterById(@PathVariable String smartMeterId) {
    final MeterDto meterDto =
        this.meterRepository
            .findById(smartMeterId)
            .map(meterMapper::entityToDto)
            .orElseThrow(() -> new MeterNotFoundException(smartMeterId));
    return ResponseEntity.ok(meterDto);
  }

  @PostMapping
  public ResponseEntity<MeterDto> createMeter(@RequestBody MeterDto meterDto) {
    if (this.meterRepository.findById(meterDto.getSmartMeterId()).isPresent()) {
      throw new MeterDuplicatedException(meterDto.getSmartMeterId());
    }
    final Meter meter = this.meterMapper.dtoToEntity(meterDto);
    final MeterDto newMeterDto = this.meterMapper.entityToDto(meterRepository.save(meter));
    return ResponseEntity.status(HttpStatus.CREATED).body(newMeterDto);
  }

  @DeleteMapping("/{smartMeterId}")
  public ResponseEntity<Object> deleteMeter(@PathVariable String smartMeterId) {
    if (!this.meterRepository.existsById(smartMeterId)) {
      throw new MeterNotFoundException(smartMeterId);
    }

    this.meterRepository.deleteById(smartMeterId);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/{smartMeterId}")
  public ResponseEntity<MeterDto> updateMeter(
      @PathVariable String smartMeterId, @RequestBody MeterDto meterDto) {
    if (!this.meterRepository.existsById(smartMeterId)) {
      throw new MeterNotFoundException(smartMeterId);
    }
    final Meter updatedMeter =
        this.meterRepository
            .findById(smartMeterId)
            .map(
                meter -> {
                  meter.setEnergySupplier(meterDto.getEnergySupplier());
                  meter.setOwner(meterDto.getOwner());
                  return this.meterRepository.save(meter);
                })
            .orElseThrow(() -> new MeterNotFoundException(smartMeterId));
    return ResponseEntity.ok(this.meterMapper.entityToDto(updatedMeter));
  }
}
