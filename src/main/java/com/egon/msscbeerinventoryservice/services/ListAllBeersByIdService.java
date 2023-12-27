package com.egon.msscbeerinventoryservice.services;

import com.egon.msscbeerinventoryservice.dtos.BeerInventoryDto;

import java.util.List;
import java.util.UUID;

public interface ListAllBeersByIdService {
  List<BeerInventoryDto> execute(UUID id);
}
