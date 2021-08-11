package com.sirenatravel.aggregator.core.service;

import com.sirenatravel.aggregator.core.domain.model.Account;

import java.util.List;

public interface AccountService {

    Account findUser(Long id);

    List<Account> findAll();

    Account saveOrUpdate(Account account);

    Account findByUserName(String username);
}
