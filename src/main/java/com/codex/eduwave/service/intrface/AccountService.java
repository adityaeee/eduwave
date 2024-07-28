package com.codex.eduwave.service.intrface;

import com.codex.eduwave.entity.Account;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

public interface AccountService extends UserDetailsService {
    Account getByAccountId (String id);
    Account getByContent();
}
