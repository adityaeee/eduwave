package com.codex.eduwave.service;

import com.codex.eduwave.entity.Pembayaran;
import com.codex.eduwave.entity.Transaksi;
import com.codex.eduwave.model.request.PembayaranDetailRequest;
import com.codex.eduwave.model.request.PembayaranJenisGolonganRequest;
import com.codex.eduwave.model.request.PembayaranRequest;
import com.codex.eduwave.model.request.PembayaranSiswaRequest;
import com.codex.eduwave.repository.PembayaranRepository;
import com.codex.eduwave.service.intrface.PembayaranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Service
public class PembayaranServiceImpl implements PembayaranService {

    private final PembayaranRepository pembayaranRepository;
    private final RestClient restClient;

    private final String SECRET_KEY;
    private final String BASE_URL_SNAP;

    @Autowired
    public PembayaranServiceImpl(
            PembayaranRepository pembayaranRepository,
            RestClient restClient,
            @Value("${midtrans.api.key}") String SECRET_KEY,
            @Value("${midtrans.api.snap-url}") String BASE_URL_SNAP

    ){
        this.pembayaranRepository = pembayaranRepository;
        this.restClient = restClient;
        this.SECRET_KEY = SECRET_KEY;
        this. BASE_URL_SNAP = BASE_URL_SNAP;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Pembayaran createPembayaran(Transaksi transaksi) {

        PembayaranDetailRequest pembayaranDetailRequest = PembayaranDetailRequest.builder()
                .orderId(transaksi.getId())
                .amount(transaksi.getJumlahBayar())
                .build();

        PembayaranJenisGolonganRequest jenisGolonganRequest = PembayaranJenisGolonganRequest.builder()
                .price(transaksi.getJumlahBayar())
                .golongan(transaksi.getSiswa().getGolongan().getGolongan())
                .quantity(1)
                .build();

        PembayaranSiswaRequest siswaRequest = PembayaranSiswaRequest.builder()
                .firstName(transaksi.getSiswa().getNama())
                .nis(transaksi.getSiswa().getNis())
                .email(transaksi.getSiswa().getEmail())
                .phone(transaksi.getSiswa().getNoHp())
                .build();

        PembayaranRequest pembayaranRequest = PembayaranRequest.builder()
                .pembayaranDetail(pembayaranDetailRequest)
                .pembayaranJenisGolongan(List.of(jenisGolonganRequest))
                .pembayaranMethod(List.of("gopay", "shopeepay", "qris", "bank_transfer", "cstore"))
                .siswa(siswaRequest)
                .build();

        ResponseEntity<Map<String, String>> responseFromMidtrans = restClient.post()
                .uri(BASE_URL_SNAP)
                .body(pembayaranRequest)
                .header(HttpHeaders.AUTHORIZATION, "Basic " + SECRET_KEY)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<>(){});

        Map<String, String> body = responseFromMidtrans.getBody();

        if(body == null) {
            return null;
        }

        Pembayaran pembayaran = Pembayaran.builder()
                .token(body.get("token"))
                .redirectUrl(body.get("redirect_url"))
                .transactionStatus("pending")
                .build();

        pembayaranRepository.saveAndFlush(pembayaran);

        return pembayaran;
    }
}
