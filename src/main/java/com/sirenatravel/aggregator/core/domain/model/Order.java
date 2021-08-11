package com.sirenatravel.aggregator.core.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "start_location")
    private String startLocation;

    @NotNull
    @Column(name = "end_location")
    private String endLocation;

    public Order() {
    }

    public Order(Long id, String startLocation, String endLocation) {
        this.id = id;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String start_location) {
        this.startLocation = start_location;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String end_location) {
        this.endLocation = end_location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id.equals(order.id) && startLocation.equals(order.startLocation) && endLocation.equals(order.endLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startLocation, endLocation);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", startLocation='" + startLocation + '\'' +
                ", endLocation='" + endLocation + '\'' +
                '}';
    }
}
