package com.sirenatravel.aggregator.lookupservice;

import com.sirenatravel.aggregator.config.Constants;
import com.sirenatravel.aggregator.core.domain.dto.AvailableTaxiAnswerDto;
import com.sirenatravel.aggregator.core.exception.CompanyDoesntExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class TaxiLookupService {

    private static final Logger LOG = LoggerFactory.getLogger(TaxiLookupService.class);

    private final RestTemplate restTemplate;

    public TaxiLookupService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<AvailableTaxiAnswerDto> findTaxi(Long orderId, String startLocation, String endLocation,
                                                              String url) {
        LOG.debug("Looking for taxi from {} to {}", startLocation, endLocation);

        var taxi = restTemplate.getForObject(url,
                AvailableTaxiAnswerDto.class, startLocation, endLocation);
        if (taxi != null) {
            taxi.setOrderId(orderId);
        }
        return CompletableFuture.completedFuture(taxi);
    }

    public UUID approveOffer(UUID uuid, String company) {
        LOG.debug("Approving offer from {} with uuid {}", company, uuid);
        return restTemplate.getForObject(companyApproveUrl(company), UUID.class, uuid);
    }

    private String companyApproveUrl(String company) {
        if (company.equalsIgnoreCase(Constants.Companies.UBER)) {
            return Constants.TaxiApprove.UBER_APPROVE;
        } else if (company.equalsIgnoreCase(Constants.Companies.YANDEX)) {
            return Constants.TaxiApprove.YANDEX_APPROVE;
        } else if (company.equalsIgnoreCase(Constants.Companies.CITY_MOBIL)) {
            return Constants.TaxiApprove.CITY_MOBIL_APPROVE;
        } else throw new CompanyDoesntExistException(String.format("Company with name %s doesnt exist", company));
    }
}
