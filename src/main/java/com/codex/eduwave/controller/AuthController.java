package com.codex.eduwave.controller;

import com.codex.eduwave.constant.ApiUrl;
import com.codex.eduwave.model.request.AuthRequest;
import com.codex.eduwave.model.response.BaseResponse;
import com.codex.eduwave.model.response.CommonResponse;
import com.codex.eduwave.model.response.LoginResponse;
import com.codex.eduwave.service.intrface.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiUrl.AUTH)
public class AuthController {

    private final AuthService authService;

    @PostMapping(path = "/login")
    public ResponseEntity<BaseResponse> login(@RequestBody AuthRequest request) {
        LoginResponse account = authService.login(request);
        CommonResponse<LoginResponse> response = CommonResponse.<LoginResponse>builder()
                .statusCode(HttpStatus.ACCEPTED.value())
                .message("You have successfully logged in")
                .data(account)
                .build();


        return ResponseEntity.ok(response);
    }
}
