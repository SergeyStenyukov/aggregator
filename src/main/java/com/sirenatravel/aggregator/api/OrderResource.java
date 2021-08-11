package com.sirenatravel.aggregator.api;

import com.sirenatravel.aggregator.config.ApiVersion;
import com.sirenatravel.aggregator.config.Constants;
import com.sirenatravel.aggregator.core.domain.converter.TypeConverter;
import com.sirenatravel.aggregator.core.domain.dto.AvailableTaxiAnswerDto;
import com.sirenatravel.aggregator.core.domain.dto.OrderDto;
import com.sirenatravel.aggregator.core.domain.model.Order;
import com.sirenatravel.aggregator.core.service.OrderService;
import com.sirenatravel.aggregator.lookupservice.TaxiLookupService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.sirenatravel.aggregator.api.OrderResource.BASE_URL;

@RestController
@RequestMapping(BASE_URL)
public class OrderResource {

    private static final Logger LOG = LoggerFactory.getLogger(OrderResource.class);

    public static final String BASE_URL = ApiVersion.VERSION_1_0 + "/orders";

    private final TypeConverter<OrderDto, Order> converter;

    private final OrderService orderService;

    private final TaxiLookupService taxiLookupService;

    public OrderResource(TypeConverter<OrderDto, Order> converter,
                         OrderService orderService, TaxiLookupService taxiLookupService) {
        this.converter = converter;
        this.orderService = orderService;
        this.taxiLookupService = taxiLookupService;
    }

    @Operation(summary = "Create order")
    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public List<AvailableTaxiAnswerDto> createNewOrder(@RequestBody @Valid OrderDto order) throws ExecutionException, InterruptedException {
        LOG.debug("Creating new order");
        var savedOrder = orderService.save(converter.convert(order));
        var uber = lookUpTaxi(savedOrder, Constants.TaxiOrders.UBER_ORDER);
        var yandex = lookUpTaxi(savedOrder, Constants.TaxiOrders.YANDEX_ORDER);
        var cityMobil = lookUpTaxi(savedOrder, Constants.TaxiOrders.CITY_MOBIL_ORDER);
        CompletableFuture.allOf(uber, yandex, cityMobil).join();
        return List.of(uber.get(), yandex.get(), cityMobil.get());
    }

    private CompletableFuture<AvailableTaxiAnswerDto> lookUpTaxi(Order order, String taxiService) {
        return taxiLookupService.findTaxi(order.getId(), order.getStartLocation(), order.getEndLocation(), taxiService);
    }
}
