package com.codex.eduwave.service;

import com.codex.eduwave.entity.Account;
import com.codex.eduwave.model.response.JwtClaims;
import com.codex.eduwave.service.intrface.JwtService;

public class JwtServiceImpl implements JwtService {
    @Override
    public String generateToken(Account account) {
        return "";
    }

    @Override
    public boolean verifyJwtToken(String token) {
        return false;
    }

    @Override
    public JwtClaims getClaimsByToken(String token) {
        return null;
    }
}
