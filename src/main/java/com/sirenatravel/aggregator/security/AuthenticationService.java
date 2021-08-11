package com.sirenatravel.aggregator.security;

import com.sirenatravel.aggregator.core.domain.model.Account;
import com.sirenatravel.aggregator.core.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        var account = accountRepository.findByUsername(email);
        if (account == null) {
            throw new UsernameNotFoundException("Account doesnt exist");
        }
        return new User(account.getUsername(), account.getPassword(), createAuthorities(account));
    }

    private Collection<GrantedAuthority> createAuthorities(Account account) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + account.getRole().toUpperCase()));
        return authorities;
    }
}
