package com.sirenatravel.aggregator.core.domain.dto;

import javax.validation.constraints.NotNull;

public class OrderDto {

    @NotNull
    private String start_location;

    @NotNull
    private String end_location;

    public String getStart_location() {
        return start_location;
    }

    public void setStart_location(String start_location) {
        this.start_location = start_location;
    }

    public String getEnd_location() {
        return end_location;
    }

    public void setEnd_location(String end_location) {
        this.end_location = end_location;
    }
}
