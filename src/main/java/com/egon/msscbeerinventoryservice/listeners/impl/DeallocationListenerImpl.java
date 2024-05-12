package com.egon.msscbeerinventoryservice.listeners.impl;

import com.egon.brewery.dtos.events.DeallocateOrderRequestDto;
import com.egon.msscbeerinventoryservice.config.JmsConfig;
import com.egon.msscbeerinventoryservice.listeners.DeallocationListener;
import com.egon.msscbeerinventoryservice.services.DeallocateOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class DeallocationListenerImpl implements DeallocationListener {

  private final DeallocateOrderService deallocateOrderService;

  @JmsListener(destination = JmsConfig.DEALLOCATE_ORDER_QUEUE)
  @Override
  public void listen(DeallocateOrderRequestDto request) {
    deallocateOrderService.execute(request.getBeerOrderDto());
  }
}
