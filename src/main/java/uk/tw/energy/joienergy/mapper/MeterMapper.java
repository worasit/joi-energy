package uk.tw.energy.joienergy.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import uk.tw.energy.joienergy.domain.Meter;
import uk.tw.energy.joienergy.dto.MeterDto;

@Mapper(componentModel = "spring")
public interface MeterMapper {
  MeterMapper INSTANCE = Mappers.getMapper(MeterMapper.class);

  @Mapping(source = "smartMeterId", target = "smartMeterId")
  @Mapping(source = "owner", target = "owner")
  @Mapping(source = "energySupplier", target = "energySupplier")
  Meter dtoToEntity(MeterDto meterDto);

  @Mapping(source = "smartMeterId", target = "smartMeterId")
  @Mapping(source = "owner", target = "owner")
  @Mapping(source = "energySupplier", target = "energySupplier")
  MeterDto entityToDto(Meter meterDto);
}
