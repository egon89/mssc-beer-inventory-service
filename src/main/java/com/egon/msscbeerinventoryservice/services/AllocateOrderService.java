package com.egon.msscbeerinventoryservice.services;

import com.egon.brewery.dtos.BeerOrderDto;

public interface AllocateOrderService {
  Boolean execute(BeerOrderDto beerOrderDto);
}
