package com.codex.eduwave.service;

import com.codex.eduwave.constant.StatusSPP;
import com.codex.eduwave.entity.Golongan;
import com.codex.eduwave.entity.Siswa;
import com.codex.eduwave.model.request.SiswaRequest;
import com.codex.eduwave.model.request.UpdateSiswaRequest;
import com.codex.eduwave.model.response.SiswaResponse;
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
import java.util.List;

@Service
@RequiredArgsConstructor
public class SiswaServiceImpl implements SiswaService {
    private final SiswaRepository siswaRepository;
    private final GolonganService golonganService;
    private final ValidationUtil validationUtil;


    @Override
    public List<Siswa> getAll() {
        return List.of();
    }

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
        return getSiswaOrElseThrowException(id);
    }

    @Override
    public Siswa update(UpdateSiswaRequest request) {
        Siswa siswa = getSiswaOrElseThrowException(request.getId());

        Golongan golongan = golonganService.getById(request.getGolonganId());

        siswa.setNama(request.getNama());
        siswa.setEmail(request.getEmail());
        siswa.setNis(request.getNis());
        siswa.setAlamat(request.getAlamat());
        siswa.setNoHp(request.getNoHp());
        siswa.setNoHpOrtu(request.getNoHpOrtu());
        siswa.setGolongan(golongan);
        siswa.setUpdatedAt(new Date());

        return siswaRepository.saveAndFlush(siswa);
    }

    @Override
    public void inActive(String id) {
        Siswa siswa = getSiswaOrElseThrowException(id);
        siswa.setIsActive(false);
        siswaRepository.saveAndFlush(siswa);
    }

    @Override
    public List<Siswa> updateStatusBulk(List<Siswa> requests) {
        return siswaRepository.saveAllAndFlush(requests);
    }

    public Siswa getSiswaOrElseThrowException(String id) {
        return siswaRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Siswa not found"));
    }

}
