package com.sirenatravel.aggregator.core.service.impl;

import com.sirenatravel.aggregator.core.domain.model.Trip;
import com.sirenatravel.aggregator.core.exception.EntityNotFoundException;
import com.sirenatravel.aggregator.core.repository.TripRepository;
import com.sirenatravel.aggregator.core.service.AccountService;
import com.sirenatravel.aggregator.core.service.TripService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TripServiceImpl implements TripService {

    private static final Logger LOG = LoggerFactory.getLogger(TripServiceImpl.class);

    private final TripRepository tripRepository;

    private final AccountService accountService;

    public TripServiceImpl(TripRepository tripRepository, AccountService accountService) {
        this.tripRepository = tripRepository;
        this.accountService = accountService;
    }

    @Override
    @Transactional
    public Trip saveTrip(String username, Trip trip) {
        LOG.debug("Saving trip for user {}", username);
        tripRepository.save(trip);
        var currentUser = accountService.findByUserName(username);
        currentUser.addTrip(trip);
        accountService.saveOrUpdate(currentUser);
        return trip;
    }

    @Override
    @Transactional
    public Trip updateTrip(String username, Trip trip) {
        LOG.debug("Updating trip for user {}", username);
        var tripForUpdate = findTripByUuidOrThrowException(trip.getOfferUuid());
        var currentUser = accountService.findByUserName(username);
        currentUser.removeTrip(tripForUpdate);
        tripForUpdate.setUpdatedDate(LocalDateTime.now());
        tripRepository.save(tripForUpdate);
        currentUser.addTrip(tripForUpdate);
        accountService.saveOrUpdate(currentUser);
        return tripForUpdate;
    }

    @Override
    @Transactional
    public void deleteTrip(String username, Trip trip) {
        LOG.debug("Declining trip for user {}", username);
        var tripForRemove = findTripByUuidOrThrowException(trip.getOfferUuid());
        tripRepository.deleteByOfferUuid(trip.getOfferUuid());
        var currentUser = accountService.findByUserName(username);
        currentUser.removeTrip(tripForRemove);
        accountService.saveOrUpdate(currentUser);
    }

    private Trip findTripByUuidOrThrowException(UUID uuid) {
        var trip = tripRepository.findByOfferUuid(uuid);
        if (trip == null) {
            throw new EntityNotFoundException("Trip you want update dosent exist");
        }
        return trip;
    }
}
