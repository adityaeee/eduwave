package com.codex.eduwave.service;

import com.codex.eduwave.entity.Golongan;
import com.codex.eduwave.entity.Sekolah;
import com.codex.eduwave.model.request.GolonganRequest;
import com.codex.eduwave.model.request.UpdateGolonganRequest;
import com.codex.eduwave.model.response.JwtClaims;
import com.codex.eduwave.model.response.SekolahResponse;
import com.codex.eduwave.repository.GolonganRepository;
import com.codex.eduwave.service.intrface.GolonganService;
import com.codex.eduwave.service.intrface.JwtService;
import com.codex.eduwave.service.intrface.SekolahService;
import com.codex.eduwave.utils.ValidationUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
public class GolonganServiceImpl implements GolonganService {

    private final GolonganRepository golonganRepository;
    private final SekolahService sekolahService;
    private final ValidationUtil validationUtil;

    final String AUTO_HEADER = "Authorization";
    private final HttpServletRequest httpServletRequest;
    private final JwtService jwtService;


    @Override
    public Golongan create(GolonganRequest request) {
        validationUtil.validate(request);

        String bearerToken = httpServletRequest.getHeader(AUTO_HEADER);
        JwtClaims jwtClaims = jwtService.getClaimsByToken(bearerToken);

        Sekolah sekolah = sekolahService.getSekolahByAccountId(jwtClaims.getAccountId());

       return golonganRepository.saveAndFlush(
               Golongan.builder()
                       .golongan(request.getGolongan())
                       .spp(request.getSpp())
                       .sekolah(sekolah)
                       .isDeleted(false)
                       .createdAt(new Date())
                       .updatedAt(new Date())
                       .build()
       );

    }

    @Override
    public Golongan getById(String id) {
        return golonganRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"data not found"));
    }

    @Override
    public List<Golongan> getAll() {
        List<Golongan> listGolongan = golonganRepository.findByIsDeletedFalse();
        return listGolongan;
    }

    @Override
    public Golongan update(UpdateGolonganRequest request) {
        Golongan golongan = getByIdIfExist(request.getId());

        return golonganRepository.saveAndFlush(Golongan.builder()
                .id(golongan.getId())
                .golongan(request.getGolongan())
                .spp(request.getSpp())
                .sekolah(golongan.getSekolah())
                .isDeleted(golongan.getIsDeleted())
                .createdAt(golongan.getCreatedAt())
                .updatedAt(new Date())
                .build());
    }

    @Override
    public void delete(String id) {
        Golongan golongan = getByIdIfExist(id);
        golongan.setIsDeleted(true);

        golonganRepository.saveAndFlush(golongan);


    }



    private Golongan getByIdIfExist(String id){
        Golongan golongan = golonganRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found"));

        if (golongan.getIsDeleted()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found");
        }
    return golongan;
    }


}
