package com.sirenatravel.aggregator.core.domain.converter.impl;

import com.sirenatravel.aggregator.core.domain.converter.TypeConverter;
import com.sirenatravel.aggregator.core.domain.dto.ApprovingTaxiOfferDto;
import com.sirenatravel.aggregator.core.domain.model.Trip;
import com.sirenatravel.aggregator.core.exception.OrderDoesntExistException;
import com.sirenatravel.aggregator.core.repository.OrderRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ApprovedTaxiAnswerDtoToTripConverter implements TypeConverter<ApprovingTaxiOfferDto, Trip> {

    private final OrderRepository orderRepository;

    public ApprovedTaxiAnswerDtoToTripConverter(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Class<ApprovingTaxiOfferDto> getSourceClass() {
        return ApprovingTaxiOfferDto.class;
    }

    @Override
    public Class<Trip> getTargetClass() {
        return Trip.class;
    }

    @Override
    public Trip convert(ApprovingTaxiOfferDto source) {
        var trip = new Trip();
        trip.setPrice(source.getPrice());
        trip.setOrder(orderRepository.findById(source.getOrderId())
                .orElseThrow(() -> new OrderDoesntExistException("You doesn't have orders to approve trip.")));
        trip.setOfferUuid(source.getOfferId());
        trip.setCreatedDate(LocalDateTime.now());
        trip.setUpdatedDate(LocalDateTime.now());
        return trip;
    }
}
