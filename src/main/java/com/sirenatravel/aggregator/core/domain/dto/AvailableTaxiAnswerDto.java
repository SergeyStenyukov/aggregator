package com.sirenatravel.aggregator.core.domain.dto;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.UUID;

public class AvailableTaxiAnswerDto {

    private UUID offerId;

    private Long orderId;

    private Long driverId;

    private String company;

    private String driverFirstName;

    private String driverLastName;

    private String startLocation;

    private String endLocation;

    private LocalTime pickUpTime;

    private String car;

    private BigDecimal price;

    public AvailableTaxiAnswerDto() {
    }

    public AvailableTaxiAnswerDto(String company, String driverFirstName, String driverLastName, String car, BigDecimal price) {
        this.company = company;
        this.driverFirstName = driverFirstName;
        this.driverLastName = driverLastName;
        this.car = car;
        this.price = price;
    }

    public UUID getOfferId() {
        return offerId;
    }

    public void setOfferId(UUID offerId) {
        this.offerId = offerId;
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

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    public LocalTime getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(LocalTime pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }
}
