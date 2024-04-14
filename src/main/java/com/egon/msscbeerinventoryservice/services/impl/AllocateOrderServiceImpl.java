package com.egon.msscbeerinventoryservice.services.impl;

import com.egon.brewery.dtos.BeerOrderDto;
import com.egon.brewery.dtos.BeerOrderLineDto;
import com.egon.msscbeerinventoryservice.repositories.BeerInventoryRepository;
import com.egon.msscbeerinventoryservice.services.AllocateOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;

@Slf4j
@RequiredArgsConstructor
@Service
public class AllocateOrderServiceImpl implements AllocateOrderService {
  private final BeerInventoryRepository repository;
  private final BiFunction<Integer, Integer, Boolean> canBeAllocated =
      (quantity, quantityAllocated) -> quantity - quantityAllocated > 0;

  @Override
  public Boolean execute(BeerOrderDto beerOrderDto) {
    log.debug("Allocating order id {}", beerOrderDto.getId().toString());

    final var totalOrdered = new AtomicInteger();
    final var totalAllocated = new AtomicInteger();

    beerOrderDto.getBeerOrderLines().forEach(orderLine -> {
      final var quantity = orderLine.getOrderQuantity();
      final var quantityAllocated = orderLine.getOrderQuantity();

      if (canBeAllocated.apply(quantity, quantityAllocated)) {
        allocateBeerOrderLine(orderLine);
      }
      totalOrdered.set(totalOrdered.get() + quantity);
      totalAllocated.set(totalAllocated.get() + quantityAllocated);
    });
    log.debug("Total Ordered: {} Total Allocated: {}", totalOrdered.get(), totalAllocated.get());

    return totalOrdered.get() == totalAllocated.get();
  }

  private void allocateBeerOrderLine(BeerOrderLineDto orderLine) {
    final var beerInventoryList = repository.findAllByUpc(orderLine.getUpc());
    beerInventoryList.forEach(beerInventory -> {
      int inventory = Optional.ofNullable(beerInventory.getQuantityOnHand()).orElse(0);
      final var orderQuantity = orderLine.getOrderQuantity();
      final var allocatedQuantity = orderLine.getQuantityAllocated();
      final var quantityToAllocate = orderQuantity - allocatedQuantity;

      if (inventory >= quantityToAllocate) {
        // full allocation
        inventory = inventory - quantityToAllocate;
        orderLine.setQuantityAllocated(orderQuantity);
        beerInventory.setQuantityOnHand(inventory);
        repository.save(beerInventory);
      } else if (inventory > 0) {
        // partial allocation
        orderLine.setQuantityAllocated(allocatedQuantity + inventory);
        beerInventory.setQuantityOnHand(0);
        repository.delete(beerInventory);
      }
    });
  }
}
