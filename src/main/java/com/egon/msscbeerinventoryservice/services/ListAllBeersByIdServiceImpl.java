package com.egon.msscbeerinventoryservice.services;

import com.egon.msscbeerinventoryservice.dtos.BeerInventoryDto;
import com.egon.msscbeerinventoryservice.mappers.BeerInventoryMapper;
import com.egon.msscbeerinventoryservice.repositories.BeerInventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ListAllBeersByIdServiceImpl implements ListAllBeersByIdService {

  private final BeerInventoryRepository repository;
  private final BeerInventoryMapper mapper;

  @Override
  public List<BeerInventoryDto> execute(UUID id) {
    return repository.findAllById(id).stream().map(mapper::toDto).toList();
  }
}
