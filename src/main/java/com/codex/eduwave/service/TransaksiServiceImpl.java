package com.codex.eduwave.service;

import com.codex.eduwave.entity.Siswa;
import com.codex.eduwave.entity.Transaksi;
import com.codex.eduwave.model.request.TransaksiRequest;
import com.codex.eduwave.model.request.UpdateTransaksiPembayaranStatusRequest;
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
        Siswa siswa = siswaService.getByNis(request.getNis());

        if(!siswa.getIsActive()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Siswa non active");
        }


        Transaksi transaksi = Transaksi.builder()
                .siswa(siswa)
                .transDate(new Date())
                
                .build();


        return null;

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
