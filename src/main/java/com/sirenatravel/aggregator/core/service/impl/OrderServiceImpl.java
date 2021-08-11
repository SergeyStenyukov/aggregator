package com.sirenatravel.aggregator.core.service.impl;

import com.sirenatravel.aggregator.core.domain.model.Order;
import com.sirenatravel.aggregator.core.repository.OrderRepository;
import com.sirenatravel.aggregator.core.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }
}
