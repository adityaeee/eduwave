package com.codex.eduwave.service;

import com.codex.eduwave.entity.Siswa;
import com.codex.eduwave.entity.Transaksi;
import com.codex.eduwave.model.request.TransaksiRequest;
import com.codex.eduwave.model.request.UpdateTransaksiPembayaranStatusRequest;
import com.codex.eduwave.model.response.SiswaResponse;
import com.codex.eduwave.model.response.TransaksiResponse;
import com.codex.eduwave.repository.TransaksiRepository;
import com.codex.eduwave.service.intrface.SiswaService;
import com.codex.eduwave.service.intrface.TransaksiService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransaksiServiceImpl implements TransaksiService {

    private final TransaksiRepository transaksiRepository;
    private final SiswaService siswaService;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public TransaksiResponse create(TransaksiRequest request) {

        Siswa checkTagihan = siswaService.getByNis(request.getNis());

        if (checkTagihan.getTagihan() < request.getJumlahBayar()) {
            request.setJumlahBayar(checkTagihan.getTagihan());
        }

        Siswa siswa = siswaService.updateStatusTagihan(request);

        Transaksi transaksi = transaksiRepository.saveAndFlush(
                Transaksi.builder()
                        .siswa(siswa)
                        .transDate(new Date())
                        .jumlahBayar(request.getJumlahBayar())
                        .build()
        );

        SiswaResponse siswaResponse = SiswaResponse.builder()
                .id(siswa.getId())
                .nama(siswa.getNama())
                .nis(siswa.getNis())
                .email(siswa.getEmail())
                .noHp(siswa.getNoHp())
                .noHpOrtu(siswa.getNoHpOrtu())
                .alamat(siswa.getAlamat())
                .status(siswa.getStatus())
                .tagihan(siswa.getTagihan())
                .golongan(siswa.getGolongan().getId())
                .isActive(siswa.getIsActive())
                .createdAt(siswa.getCreatedAt())
                .updatedAt(siswa.getUpdatedAt())
                .build();

        return TransaksiResponse.builder()
                .id(transaksi.getId())
                .jumlahBayar(request.getJumlahBayar())
                .transDate(transaksi.getTransDate())
                .siswa(siswaResponse)
                .build();
    }

    @Override
    public List<TransaksiResponse> getAll() {
        return List.of();
    }

    @Override
    public List<TransaksiResponse> getAllByIdSiswa(String id) {
        return List.of();
    }

    @Override
    public void updateStatus(UpdateTransaksiPembayaranStatusRequest request) {

    }
}