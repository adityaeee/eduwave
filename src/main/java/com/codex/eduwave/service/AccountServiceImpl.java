package com.codex.eduwave.service;

import com.codex.eduwave.entity.Account;
import com.codex.eduwave.repository.AccountRepository;
import com.codex.eduwave.service.intrface.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Transactional(readOnly = true)
    @Override
    public Account getByAccountId(String id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
    }

    @Override
    public Account getByContent() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return accountRepository.findByUsername(authentication.getPrincipal().toString())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found from context")
                );
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }
}
