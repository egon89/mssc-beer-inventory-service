package com.egon.msscbeerinventoryservice.listeners.impl;

import com.egon.common.events.dtos.EventDto;
import com.egon.common.events.dtos.NewInventoryEventDto;
import com.egon.msscbeerinventoryservice.config.JmsConfig;
import com.egon.msscbeerinventoryservice.listeners.NewInventoryListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class NewInventoryListenerImpl implements NewInventoryListener {

  @JmsListener(destination = JmsConfig.NEW_INVENTORY_QUEUE)
  public void listen(NewInventoryEventDto eventDto) {
    val newInventoryBeerDto = Optional.ofNullable(eventDto).map(EventDto::getData).orElseThrow(IllegalAccessError::new);
    log.debug("New inventory listener: Beer {}, Quantity on Hand: {}",
        newInventoryBeerDto.id(), newInventoryBeerDto.quantityOnHand());
  }
}
