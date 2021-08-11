package com.sirenatravel.aggregator.core.domain.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "trips")
public class Trip extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private BigDecimal price;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;

    @NotNull
    @Column(name = "offer_uuid")
    private UUID offerUuid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public UUID getOfferUuid() {
        return offerUuid;
    }

    public void setOfferUuid(UUID offerUuid) {
        this.offerUuid = offerUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return id.equals(trip.id) && price.equals(trip.price) && order.equals(trip.order) && offerUuid.equals(trip.offerUuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, order, offerUuid);
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", price=" + price +
                ", order=" + order +
                ", offerUuid=" + offerUuid +
                '}';
    }
}
