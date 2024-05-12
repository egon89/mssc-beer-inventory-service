package com.egon.msscbeerinventoryservice.listeners;

import com.egon.brewery.dtos.events.DeallocateOrderRequestDto;

public interface DeallocationListener {
  void listen(DeallocateOrderRequestDto request);
}
