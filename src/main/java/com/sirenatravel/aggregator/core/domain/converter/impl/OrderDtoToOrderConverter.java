package com.sirenatravel.aggregator.core.domain.converter.impl;

import com.sirenatravel.aggregator.core.domain.converter.TypeConverter;
import com.sirenatravel.aggregator.core.domain.dto.OrderDto;
import com.sirenatravel.aggregator.core.domain.model.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OrderDtoToOrderConverter implements TypeConverter<OrderDto, Order> {

    @Override
    public Class<OrderDto> getSourceClass() {
        return OrderDto.class;
    }

    @Override
    public Class<Order> getTargetClass() {
        return Order.class;
    }

    @Override
    public Order convert(OrderDto source) {
        var order = new Order();
        order.setStartLocation(source.getStart_location());
        order.setEndLocation(source.getEnd_location());
        order.setCreatedDate(LocalDateTime.now());
        order.setUpdatedDate(LocalDateTime.now());
        return order;
    }
}
