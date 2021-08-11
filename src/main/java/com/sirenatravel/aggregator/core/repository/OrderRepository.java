package com.sirenatravel.aggregator.core.repository;

import com.sirenatravel.aggregator.core.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
