package com.codex.eduwave.service.intrface;

import com.codex.eduwave.entity.Account;
import com.codex.eduwave.model.request.AuthRequest;
import com.codex.eduwave.model.response.LoginResponse;
import com.codex.eduwave.model.response.RegisterResponse;

public interface AuthService {
    Account register(AuthRequest request);
    LoginResponse login(AuthRequest request);
}
