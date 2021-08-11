package com.sirenatravel.aggregator.api;

import com.sirenatravel.aggregator.config.ApiVersion;
import com.sirenatravel.aggregator.core.domain.converter.TypeConverter;
import com.sirenatravel.aggregator.core.domain.dto.ApprovingTaxiOfferDto;
import com.sirenatravel.aggregator.core.domain.model.Trip;
import com.sirenatravel.aggregator.core.service.TripService;
import com.sirenatravel.aggregator.lookupservice.TaxiLookupService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.sirenatravel.aggregator.api.TripResource.BASE_URL;

@RestController
@RequestMapping(BASE_URL)
public class TripResource {

    private static final Logger LOG = LoggerFactory.getLogger(OrderResource.class);

    public static final String BASE_URL = ApiVersion.VERSION_1_0 + "/trips";

    private final TripService tripService;

    private final TaxiLookupService taxiLookupService;

    private final TypeConverter<ApprovingTaxiOfferDto, Trip> converter;

    public TripResource(TripService tripService, TaxiLookupService taxiLookupService,
                        TypeConverter<ApprovingTaxiOfferDto, Trip> converter) {
        this.tripService = tripService;
        this.taxiLookupService = taxiLookupService;
        this.converter = converter;
    }

    @PostMapping("/approve")
    @Operation(summary = "Approving taxi offer")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Trip approveTrip(@RequestBody @Valid ApprovingTaxiOfferDto approvingTaxiOffer, Authentication authentication) {
        LOG.debug("Approving taxi offer with uuid {}", approvingTaxiOffer.getOfferId());
        taxiLookupService.approveOffer(approvingTaxiOffer.getOfferId(), approvingTaxiOffer.getCompany());
        var trip = converter.convert(approvingTaxiOffer);
        return tripService.saveTrip(authentication.getName(), trip);
    }

    @PutMapping("/finish")
    @Operation(summary = "Finish the trip")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Trip finishTrip(@RequestBody @Valid ApprovingTaxiOfferDto approvingTaxiOffer, Authentication authentication) {
        LOG.debug("Finishing trip with uuid {}", approvingTaxiOffer.getOfferId());
        var trip = converter.convert(approvingTaxiOffer);
        return tripService.updateTrip(authentication.getName(), trip);
    }

    @DeleteMapping("/decline")
    @Operation(summary = "Decline the trip")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void declineTrip(@RequestBody @Valid ApprovingTaxiOfferDto approvingTaxiOffer, Authentication authentication) {
        LOG.debug("Finishing trip with uuid {}", approvingTaxiOffer.getOfferId());
        var trip = converter.convert(approvingTaxiOffer);
        tripService.deleteTrip(authentication.getName(), trip);
    }
}
