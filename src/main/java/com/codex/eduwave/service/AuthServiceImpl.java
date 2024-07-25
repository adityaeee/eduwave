package com.codex.eduwave.service;

import com.codex.eduwave.model.request.AuthRequest;
import com.codex.eduwave.model.response.LoginResponse;
import com.codex.eduwave.model.response.RegisterResponse;
import com.codex.eduwave.service.intrface.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    @Override
    public RegisterResponse register(AuthRequest request) {
        return null;
    }

    @Override
    public LoginResponse login(AuthRequest request) {
        return null;
    }
}
