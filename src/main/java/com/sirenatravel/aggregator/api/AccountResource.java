package com.sirenatravel.aggregator.api;

import com.sirenatravel.aggregator.config.ApiVersion;
import com.sirenatravel.aggregator.core.domain.model.Account;
import com.sirenatravel.aggregator.core.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.sirenatravel.aggregator.api.AccountResource.BASE_URL;

@RestController
@RequestMapping(BASE_URL)
public class AccountResource {

    public static final String BASE_URL = ApiVersion.VERSION_1_0 + "/accounts";

    private final AccountService accountService;

    public AccountResource(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/info")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @Operation(summary = "Get user profile")
    public Account showAccountInfo(Authentication authentication) {
        return accountService.findByUserName(authentication.getName());
    }
}
