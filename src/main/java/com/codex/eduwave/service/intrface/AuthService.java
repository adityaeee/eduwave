package com.codex.eduwave.service.intrface;

import com.codex.eduwave.model.request.AuthRequest;
import com.codex.eduwave.model.response.LoginResponse;
import com.codex.eduwave.model.response.RegisterResponse;

public interface AuthService {
    RegisterResponse register(AuthRequest request);
    LoginResponse login(AuthRequest request);
}
