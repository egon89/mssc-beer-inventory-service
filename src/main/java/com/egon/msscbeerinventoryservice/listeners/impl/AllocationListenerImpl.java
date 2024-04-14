package com.egon.msscbeerinventoryservice.listeners.impl;

import com.egon.brewery.dtos.events.AllocateOrderRequestDto;
import com.egon.brewery.dtos.events.AllocateOrderResultDto;
import com.egon.msscbeerinventoryservice.config.JmsConfig;
import com.egon.msscbeerinventoryservice.listeners.AllocationListener;
import com.egon.msscbeerinventoryservice.services.AllocateOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class AllocationListenerImpl implements AllocationListener {
  private final AllocateOrderService allocateOrderService;
  private final JmsTemplate jmsTemplate;

  @JmsListener(destination = JmsConfig.ALLOCATE_ORDER_QUEUE)
  @Override
  public void listen(AllocateOrderRequestDto request) {
    final var allocateOrderResultBuilder =
        AllocateOrderResultDto.builder()
            .beerOrderDto(request.getBeerOrderDto());
    try {
      final var allocationResult = allocateOrderService.execute(request.getBeerOrderDto());
      allocateOrderResultBuilder.pendingInventory(allocationResult);
    } catch (Exception e) {
      log.error("Allocation failed for order {}", request.getBeerOrderDto().getId());
      allocateOrderResultBuilder.allocationError(true);
    }

    try {
      jmsTemplate.convertAndSend(
          JmsConfig.ALLOCATE_ORDER_RESPONSE_QUEUE, allocateOrderResultBuilder.build());
      log.debug("Sent allocation order for order {} to {} queue",
          request.getBeerOrderDto().getId(), JmsConfig.ALLOCATE_ORDER_RESPONSE_QUEUE);
    } catch (Exception e) {
      log.error("Error to send allocation order for order {} to {} queue",
          request.getBeerOrderDto().getId(), JmsConfig.ALLOCATE_ORDER_RESPONSE_QUEUE);
    }
  }
}
