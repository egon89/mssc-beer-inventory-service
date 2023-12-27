package com.egon.msscbeerinventoryservice.mappers;

import com.egon.msscbeerinventoryservice.dtos.BeerInventoryDto;
import com.egon.msscbeerinventoryservice.entities.BeerInventoryEntity;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
public interface BeerInventoryMapper {
  BeerInventoryEntity toEntity(BeerInventoryDto dto);

  BeerInventoryDto toDto(BeerInventoryEntity entity);
}
