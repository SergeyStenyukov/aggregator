package com.sirenatravel.aggregator.core.domain.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class ApprovingTaxiOfferDto {

    private UUID offerUuid;

    private Long orderId;

    private String company;

    private String driverFirstName;

    private String driverLastName;

    private BigDecimal price;

    public UUID getOfferId() {
        return offerUuid;
    }

    public void setOfferId(UUID offerUuid) {
        this.offerUuid = offerUuid;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDriverFirstName() {
        return driverFirstName;
    }

    public void setDriverFirstName(String driverFirstName) {
        this.driverFirstName = driverFirstName;
    }

    public String getDriverLastName() {
        return driverLastName;
    }

    public void setDriverLastName(String driverLastName) {
        this.driverLastName = driverLastName;
    }


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
