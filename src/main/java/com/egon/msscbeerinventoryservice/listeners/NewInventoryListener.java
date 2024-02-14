package com.egon.msscbeerinventoryservice.listeners;

import com.egon.common.events.dtos.NewInventoryEventDto;

public interface NewInventoryListener {
  void listen(NewInventoryEventDto eventDto);
}
