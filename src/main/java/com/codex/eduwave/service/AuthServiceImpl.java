package com.codex.eduwave.service;

import com.codex.eduwave.constant.UserRole;
import com.codex.eduwave.entity.Account;
import com.codex.eduwave.entity.Role;
import com.codex.eduwave.model.request.AuthRequest;
import com.codex.eduwave.model.response.LoginResponse;
import com.codex.eduwave.model.response.RegisterResponse;
import com.codex.eduwave.repository.AccountRepository;
import com.codex.eduwave.service.intrface.AuthService;
import com.codex.eduwave.service.intrface.JwtService;
import com.codex.eduwave.service.intrface.RoleService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AccountRepository accountRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    @Value("${app.eduwave.username-admin}")
    private String AdminUsername;

    @Value("${app.eduwave.password-admin}")
    private String AdminPassword;

    @Transactional(rollbackFor = Exception.class)
    @PostConstruct
    public void initialSuperAdmin() {
        Optional<Account> currentAccount = accountRepository.findByUsername(AdminUsername);
        if(currentAccount.isPresent()) {
            return;
        } else {
            Role admin = roleService.getOrSave(UserRole.ROLE_ADMIN);
            Role sekolah = roleService.getOrSave(UserRole.ROLE_SEKOLAH);

            Account account = Account.builder()
                    .username(AdminUsername)
                    .password(passwordEncoder.encode(AdminPassword))
                    .role(List.of(admin, sekolah))
                    .isEnable(true)
                    .build();

            accountRepository.save(account);
        }
    }

    @Override
    public Account register(AuthRequest request) {
        Role role = roleService.getOrSave(UserRole.ROLE_SEKOLAH);

        String hasPassword = passwordEncoder.encode(request.getPassword());

        Account account = Account.builder()
                .username(request.getUsername())
                .password(hasPassword)
                .role(List.of(role))
                .isEnable(true)
                .build();

        accountRepository.saveAndFlush(account);
        return account;
    }

    @Override
    public LoginResponse login(AuthRequest request) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        );

        Authentication authenticated = authenticationManager.authenticate(authentication);

        Account account = (Account) authenticated.getPrincipal();

        String token = jwtService.generateToken(account);

        return LoginResponse.builder()
                .token(token)
                .username(account.getUsername())
                .roles(account.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .build();
    }
}
