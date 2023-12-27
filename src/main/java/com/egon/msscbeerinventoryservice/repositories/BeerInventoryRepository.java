package com.egon.msscbeerinventoryservice.repositories;

import com.egon.msscbeerinventoryservice.entities.BeerInventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BeerInventoryRepository extends JpaRepository<BeerInventoryEntity, UUID> {
  List<BeerInventoryEntity> findAllById(UUID id);
}
