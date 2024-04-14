package com.egon.brewery.dtos.events;

import com.egon.brewery.dtos.BeerOrderDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AllocateOrderRequestDto {
  private BeerOrderDto beerOrderDto;
}
