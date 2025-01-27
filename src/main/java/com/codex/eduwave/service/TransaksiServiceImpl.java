package com.codex.eduwave.service;

import com.codex.eduwave.entity.Pembayaran;
import com.codex.eduwave.entity.Sekolah;
import com.codex.eduwave.entity.Siswa;
import com.codex.eduwave.entity.Transaksi;
import com.codex.eduwave.model.request.TransaksiRequest;
import com.codex.eduwave.model.request.UpdateStatusTagihanSiswaRequest;
import com.codex.eduwave.model.request.UpdateTransaksiPembayaranStatusRequest;
import com.codex.eduwave.model.response.PembayaranResponse;
import com.codex.eduwave.model.response.SiswaResponse;
import com.codex.eduwave.model.response.TransaksiResponse;
import com.codex.eduwave.model.response.TransaksiSiswaResponse;
import com.codex.eduwave.repository.TransaksiRepository;
import com.codex.eduwave.service.intrface.PembayaranService;
import com.codex.eduwave.service.intrface.SekolahService;
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
    private final PembayaranService pembayaranService;
    private final SekolahService sekolahService;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public TransaksiResponse create(TransaksiRequest request) {

        Sekolah sekolah = sekolahService.getById(request.getSekolahId());

        List<Siswa> siswaBySekolahId = siswaService.getSiswaBySekolahId(sekolah.getId());

        boolean checkNis = siswaBySekolahId.stream().anyMatch(siswa -> siswa.getNis().equals(request.getNis()));

        if (!checkNis) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student are not registered at this School");
        }

        Siswa siswa = siswaService.getByNis(request.getNis());

        if(!siswa.getIsActive()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student is inactive");
        }
        if (siswa.getTagihan() < request.getJumlahBayar()) {
            request.setJumlahBayar(siswa.getTagihan());
        }

        Transaksi transaksi = transaksiRepository.saveAndFlush(
                Transaksi.builder()
                        .siswa(siswa)
                        .transDate(new Date())
                        .jumlahBayar(request.getJumlahBayar())
                        .build()
        );

        Pembayaran pembayaran = pembayaranService.createPembayaran(transaksi);
        transaksi.setPembayaran(pembayaran);

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

        PembayaranResponse pembayaranResponse = PembayaranResponse.builder()
                .id(pembayaran.getId())
                .token(pembayaran.getToken())
                .redirectUrl(pembayaran.getRedirectUrl())
                .transactionStatus(pembayaran.getTransactionStatus())
                .build();

        return TransaksiResponse.builder()
                .id(transaksi.getId())
                .jumlahBayar(request.getJumlahBayar())
                .transDate(transaksi.getTransDate())
                .siswa(siswaResponse)
                .pembayaran(pembayaranResponse)
                .build();
    }

    @Override
    public List<TransaksiSiswaResponse> getAllByIdSiswa(String siswaId) {
        List<Transaksi> transaksiList = transaksiRepository.findBySiswaId(siswaId);

        return  transaksiList.stream().map(
                transaksi -> {
                    return TransaksiSiswaResponse.builder()
                            .id(transaksi.getId())
                            .siswaId(transaksi.getSiswa().getId())
                            .namaSiswa(transaksi.getSiswa().getNama())
                            .nis(transaksi.getSiswa().getNis())
                            .golongan(transaksi.getSiswa().getGolongan().getGolongan())
                            .jumlahPembayaran(transaksi.getJumlahBayar())
                            .statusPembayaran(transaksi.getPembayaran().getTransactionStatus())
                            .tanggalTransaksi(transaksi.getTransDate())
                            .build();
                }
        ).toList();
    }

    @Override
    public Transaksi getById(String id) {
        return transaksiRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found"));
    }


    @Transactional(rollbackOn = Exception.class)
    @Override
    public void updateStatus(UpdateTransaksiPembayaranStatusRequest request) {
        Transaksi transaksi = transaksiRepository.findById(request.getOrderId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction Not Found"));

        Pembayaran pembayaran = transaksi.getPembayaran();
        pembayaran.setTransactionStatus(request.getTransactionStatus());

//        settlement
        if (request.getTransactionStatus().equals("settlement")) {
            UpdateStatusTagihanSiswaRequest updateStatusTagihanSiswa = UpdateStatusTagihanSiswaRequest.builder()
                    .siswaId(transaksi.getSiswa().getId())
                    .jumlahBayar(transaksi.getJumlahBayar())
                    .build();

            siswaService.updateStatusTagihan(updateStatusTagihanSiswa);
        }
    }
}
