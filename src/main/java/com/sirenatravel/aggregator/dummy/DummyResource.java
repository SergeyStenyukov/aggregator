package com.sirenatravel.aggregator.dummy;

import com.sirenatravel.aggregator.core.domain.dto.AvailableTaxiAnswerDto;
import net.bytebuddy.utility.RandomString;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Random;
import java.util.UUID;

@RestController
public class DummyResource {

    @GetMapping("/uber/taxi/{start_location}/{end_location}")
    public ResponseEntity<AvailableTaxiAnswerDto> getAvailableUberTaxi(@PathVariable String start_location,
                                                                       @PathVariable String end_location) {
        return ResponseEntity.ok(generateRandomTaxiData("uber", start_location, end_location));
    }

    @GetMapping("/yandex/taxi/{start_location}/{end_location}")
    public ResponseEntity<AvailableTaxiAnswerDto> getAvailableYandexTaxi(@PathVariable String start_location,
                                                                         @PathVariable String end_location) {
        return ResponseEntity.ok(generateRandomTaxiData("yandex", start_location, end_location));
    }

    @GetMapping("/citymobil/taxi/{start_location}/{end_location}")
    public ResponseEntity<AvailableTaxiAnswerDto> getAvailableCityMobilTaxi(@PathVariable String start_location,
                                                                            @PathVariable String end_location) {
        return ResponseEntity.ok(generateRandomTaxiData("citymobil", start_location, end_location));
    }

    @GetMapping("/uber/taxi/approve/{uuid}")
    public ResponseEntity<UUID> approveOfferUberTaxi(@PathVariable UUID uuid) {
        return ResponseEntity.accepted().body(uuid);
    }

    @GetMapping("/yandex/taxi/approve/{uuid}")
    public ResponseEntity<UUID> approveOfferYandexTaxi(@PathVariable UUID uuid) {
        return ResponseEntity.accepted().body(uuid);
    }

    @GetMapping("/citymobil/taxi/approve/{uuid}")
    public ResponseEntity<UUID> approveOfferCityMobilTaxi(@PathVariable UUID uuid) {
        return ResponseEntity.accepted().body(uuid);
    }

    private AvailableTaxiAnswerDto generateRandomTaxiData(String company, String from, String to) {
        var taxi = new AvailableTaxiAnswerDto();
        taxi.setDriverId((long) new Random().nextInt(1000));
        taxi.setOfferId(UUID.randomUUID());
        taxi.setCar(RandomString.make());
        taxi.setCompany(company);
        taxi.setDriverFirstName(RandomString.make());
        taxi.setDriverLastName(RandomString.make());
        taxi.setPrice(new BigDecimal(new Random().nextInt(1000)));
        taxi.setStartLocation(from);
        taxi.setEndLocation(to);
        taxi.setPickUpTime(LocalTime.now().plusMinutes(new Random().nextInt(15)));
        return taxi;
    }


}
