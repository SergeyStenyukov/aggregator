package com.sirenatravel.aggregator.core.service;

import com.sirenatravel.aggregator.core.domain.model.Trip;

public interface TripService {

    Trip saveTrip(String username, Trip trip);

    Trip updateTrip(String username, Trip trip);

    void deleteTrip(String username, Trip trip);
}
