package com.egon.msscbeerinventoryservice.services;

import com.egon.brewery.dtos.BeerOrderDto;

public interface DeallocateOrderService {
  void execute(BeerOrderDto beerOrderDto);
}
