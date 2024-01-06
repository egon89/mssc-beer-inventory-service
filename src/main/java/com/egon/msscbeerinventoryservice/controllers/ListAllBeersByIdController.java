package com.egon.msscbeerinventoryservice.controllers;

import com.egon.msscbeerinventoryservice.dtos.BeerInventoryDto;
import com.egon.msscbeerinventoryservice.services.ListAllBeersByIdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("beers")
@RequiredArgsConstructor
public class ListAllBeersByIdController {

  private final ListAllBeersByIdService service;

  @GetMapping("/{id}/inventory")
  public ResponseEntity<List<BeerInventoryDto>> listAllBeersById(
      @PathVariable UUID id) {
    return ResponseEntity.ok(service.execute(id));
  }
}
