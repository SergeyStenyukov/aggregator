package com.sirenatravel.aggregator.core.repository;

import com.sirenatravel.aggregator.core.domain.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByUsername(String email);
}

