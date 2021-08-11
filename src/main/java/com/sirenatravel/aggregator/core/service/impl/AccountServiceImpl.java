package com.sirenatravel.aggregator.core.service.impl;

import com.sirenatravel.aggregator.core.domain.model.Account;
import com.sirenatravel.aggregator.core.repository.AccountRepository;
import com.sirenatravel.aggregator.core.service.AccountService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findUser(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Account doesnt exist"));
    }

    @Override
    public Account saveOrUpdate(Account account) {
        return accountRepository.saveAndFlush(account);
    }

    @Override
    public Account findByUserName(String username) {
        return accountRepository.findByUsername(username);
    }
}
