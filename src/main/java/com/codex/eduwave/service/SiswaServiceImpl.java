package com.codex.eduwave.service;

import com.codex.eduwave.constant.StatusSPP;
import com.codex.eduwave.entity.Golongan;
import com.codex.eduwave.entity.Siswa;
import com.codex.eduwave.model.request.SiswaRequest;
import com.codex.eduwave.repository.SiswaRepository;
import com.codex.eduwave.service.intrface.GolonganService;
import com.codex.eduwave.service.intrface.SiswaService;
import com.codex.eduwave.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class SiswaServiceImpl implements SiswaService {
    private final SiswaRepository siswaRepository;
    private final GolonganService golonganService;
    private final ValidationUtil validationUtil;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Siswa create(SiswaRequest request) {
        validationUtil.validate(request);
        Golongan golongan = golonganService.getById(request.getGolonganId());



       return siswaRepository.saveAndFlush( Siswa.builder()
               .nama(request.getNama())
               .nis(request.getNis())
               .email(request.getEmail())
               .noHp(request.getNoHp())
               .noHpOrtu(request.getNoHpOrtu())
               .alamat(request.getAlamat())
               .status(StatusSPP.LUNAS)
               .tagihan(0)
               .isActive(true)
               .golongan(golongan)
               .createdAt(new Date())
               .updatedAt(new Date())
               .build());




    }

    @Override
    public Siswa getById(String id) {
        return siswaRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"data not found"));
    }
}
