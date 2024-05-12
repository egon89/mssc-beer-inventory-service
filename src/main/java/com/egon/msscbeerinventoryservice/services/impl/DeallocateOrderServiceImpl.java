package com.egon.msscbeerinventoryservice.services.impl;

import com.egon.brewery.dtos.BeerOrderDto;
import com.egon.msscbeerinventoryservice.entities.BeerInventoryEntity;
import com.egon.msscbeerinventoryservice.repositories.BeerInventoryRepository;
import com.egon.msscbeerinventoryservice.services.DeallocateOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class DeallocateOrderServiceImpl implements DeallocateOrderService {

  private final BeerInventoryRepository repository;

  @Override
  public void execute(BeerOrderDto beerOrderDto) {
    beerOrderDto.getBeerOrderLines().forEach(beerOrderLineDto -> {
      final var beerInventory = BeerInventoryEntity.builder()
          .id(beerOrderLineDto.getBeerId())
          .upc(beerOrderLineDto.getUpc())
          .quantityOnHand(beerOrderLineDto.getQuantityAllocated())
          .build();
      final var savedInventory = repository.save(beerInventory);
      log.debug("Saved Inventory for beer upc: {} inventory id: {}",
          savedInventory.getUpc(), savedInventory.getId());
    });
  }
}
