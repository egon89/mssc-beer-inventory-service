package com.egon.msscbeerinventoryservice.listeners;

import com.egon.brewery.dtos.events.AllocateOrderRequestDto;

public interface AllocationListener {
  void listen(AllocateOrderRequestDto request);
}
