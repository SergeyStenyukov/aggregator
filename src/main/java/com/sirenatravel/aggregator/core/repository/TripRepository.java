package com.sirenatravel.aggregator.core.repository;

import com.sirenatravel.aggregator.core.domain.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TripRepository extends JpaRepository<Trip, Long> {

    Trip findByOfferUuid(UUID uuid);

    void deleteByOfferUuid(UUID uuid);
}
