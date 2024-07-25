package com.codex.eduwave.service.intrface;

import com.codex.eduwave.entity.Account;
import com.codex.eduwave.model.response.JwtClaims;

public interface JwtService {
    String generateToken(Account account);

    boolean verifyJwtToken(String token);

    JwtClaims getClaimsByToken (String token);
}
