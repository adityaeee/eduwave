package com.codex.eduwave.service;

import com.codex.eduwave.entity.Account;
import com.codex.eduwave.entity.Image;
import com.codex.eduwave.entity.Sekolah;
import com.codex.eduwave.model.request.AuthRequest;
import com.codex.eduwave.model.request.SekolahRequest;
import com.codex.eduwave.model.request.UpdateSekolahRequest;
import com.codex.eduwave.model.response.JwtClaims;
import com.codex.eduwave.repository.SekolahRepository;
import com.codex.eduwave.service.intrface.*;
import com.codex.eduwave.utils.ValidationUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SekolahServiceImpl implements SekolahService {

    private final SekolahRepository sekolahRepository;
    private final ImageService imageService;
    private final AuthService authService;
    private final AccountService accountService;
    private final ValidationUtil validationUtil;

    final String AUTO_HEADER = "Authorization";
    private final HttpServletRequest httpServletRequest;
    private final JwtService jwtService;

    @Override
    public List<Sekolah> getAllSekolah() {
        List<Sekolah> listSekolah = sekolahRepository.findByIsDeletedFalse();
        return listSekolah;

    }

    @Override
    public Sekolah createSekolah(SekolahRequest request) {
        validationUtil.validate(request);

        Image logoUrl = imageService.create(request.getLogo());
        AuthRequest authRequest = AuthRequest.builder()
                .username(request.getNpsn())
                .password(request.getPassword())
                .build();
        Account sekolahAccount = authService.register(authRequest);

        String bearerToken = httpServletRequest.getHeader(AUTO_HEADER);
        JwtClaims jwtClaims = jwtService.getClaimsByToken(bearerToken);

        Account accountCreated = accountService.getByAccountId(jwtClaims.getAccountId());

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
                        .createdBy(accountCreated.getUsername())
                        .build()

        );
        return Sekolah.builder()
                .id(sekolah.getId())
                .sekolah(sekolah.getSekolah())
                .email(sekolah.getEmail())
                .noHp(sekolah.getNoHp())
                .npsn(sekolah.getNpsn())
                .logo(sekolah.getLogo())
                .golonganSekolah(sekolah.getGolonganSekolah())
                .createdBy(sekolah.getCreatedBy())
                .build();
    }

    @Override
    public Sekolah getById(String id) {
       return getByidIfExist(id);
    }

    @Override
    public Sekolah update(UpdateSekolahRequest request) {
        Sekolah sekolah = getByidIfExist(request.getId());
        String idLogoSekolahLama = null;

        if (sekolah.getLogo() != null) {
            idLogoSekolahLama = sekolah.getLogo().getId();
        }

        sekolah.setSekolah(request.getSekolah());
        sekolah.setEmail(request.getEmail());
        sekolah.setNoHp(request.getNoHp());

        if (request.getLogo() != null && !request.getLogo().isEmpty()) {
            Image image = imageService.create(request.getLogo());
            sekolah.setLogo(image);
        }

        sekolah.setUpdatedAt(new Date());

        Sekolah updateSekolah = sekolahRepository.saveAndFlush(sekolah);

        if (idLogoSekolahLama != null && !idLogoSekolahLama.equals(sekolah.getLogo().getId())) {
            imageService.deleteFromImageKit(idLogoSekolahLama);
            imageService.deleteFromEntity(idLogoSekolahLama);
        }

        return updateSekolah;
    }

    @Override
    public void delete(String id) {
        Sekolah sekolah = getByidIfExist(id);
        sekolah.setIsDeleted(true);

        sekolahRepository.saveAndFlush(sekolah);
    }

    @Override
    public Sekolah getSekolahByAccountId(String accountId) {
      return sekolahRepository.findByAccountId(accountId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data sekolah not found"));
    }


    private Sekolah getByidIfExist(String id) {
        Sekolah sekolah = sekolahRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found"));

        if(sekolah.getIsDeleted()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found");
        }

        return sekolah;
    }
}
