package com.codex.eduwave.service;

import com.codex.eduwave.entity.Account;
import com.codex.eduwave.entity.Sekolah;
import com.codex.eduwave.model.request.AuthRequest;
import com.codex.eduwave.model.request.SekolahRequest;
import com.codex.eduwave.model.response.SekolahResponse;
import com.codex.eduwave.repository.SekolahRepository;
import com.codex.eduwave.service.intrface.AuthService;
import com.codex.eduwave.service.intrface.ImageService;
import com.codex.eduwave.service.intrface.SekolahService;
import com.codex.eduwave.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class SekolahServiceImpl implements SekolahService {

    private final SekolahRepository sekolahRepository;
    private final ImageService imageService;
    private final AuthService authService;
    private final ValidationUtil validationUtil;


    @Override
    public SekolahResponse createSekolah(SekolahRequest request) {
        validationUtil.validate(request);

        String logoUrl = imageService.create(request.getLogo());
        AuthRequest authRequest = AuthRequest.builder()
                .username(request.getNpsn())
                .password(request.getPassword())
                .build();
        Account sekolahAccount = authService.register(authRequest);


        Sekolah sekolah = sekolahRepository.saveAndFlush(
                Sekolah.builder()
                        .createdAt(new Date())
                        .updatedAt(new Date())
                        .sekolah(request.getSekolah())
                        .email(request.getEmail())
                        .noHp(request.getNoHp())
                        .npsn(request.getNpsn())
                        .logo(logoUrl)
                        .account(sekolahAccount)
                        .isDeleted(false)
                        .createdBy(request.getCreatedBy())
                        .build()

        );
        return SekolahResponse.builder()
                .id(sekolah.getId())
                .sekolah(sekolah.getSekolah())
                .email(sekolah.getEmail())
                .noHp(sekolah.getNoHp())
                .npsn(sekolah.getNpsn())
                .logo(sekolah.getLogo())
                .golonganSekolah(sekolah.getGolonganSekolah())
                .role(sekolah.getAccount().getRole().stream().map((role -> {
                    return role.getRole().toString();
                })).toList())
                .createdBy(sekolah.getCreatedBy())
                .build();



    }

    @Override
    public Sekolah getById(String id) {
      return  sekolahRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found"));

    }
}
