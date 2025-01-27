package com.codex.eduwave.security;

import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfiguration {
    private final AuthenticationFilter authenticationFilter;

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(request ->  request
                        .dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers("/api/v1/transaksi/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/v1/siswa/login").permitAll()
//                        .requestMatchers(HttpMethod.GET,"/api/v1/siswa/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/v1/sekolah/list-sekolah").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/v1/siswa/bayar/{nis}").permitAll()

                        .anyRequest().authenticated()
                )
                .addFilterBefore(
                        authenticationFilter,

                        UsernamePasswordAuthenticationFilter.class
                )
                .addFilterBefore(
                        new NgrokSkipBrowserWarning(),
                        UsernamePasswordAuthenticationFilter.class
                )
                .build();
    }
}
