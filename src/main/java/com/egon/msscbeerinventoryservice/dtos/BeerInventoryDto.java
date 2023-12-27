package com.egon.msscbeerinventoryservice.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
public class BeerInventoryDto {
  private UUID id;

  private String upc;

  private Integer quantityOnHand;

  private OffsetDateTime createdAt;

  private OffsetDateTime updatedAt;

  private Long version;
}
