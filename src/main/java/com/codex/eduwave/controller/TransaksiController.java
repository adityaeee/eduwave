package com.codex.eduwave.controller;

import com.codex.eduwave.constant.ApiUrl;
import com.codex.eduwave.entity.Transaksi;
import com.codex.eduwave.model.request.TransaksiRequest;
import com.codex.eduwave.model.request.UpdateTransaksiPembayaranStatusRequest;
import com.codex.eduwave.model.response.*;
import com.codex.eduwave.service.intrface.TransaksiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiUrl.TRANSAKSI_API)
public class TransaksiController {
    private final TransaksiService transaksiService;

    @PostMapping
    public ResponseEntity<BaseResponse> createNewTransaksi(@RequestBody TransaksiRequest request) {
        TransaksiResponse transaksi = transaksiService.create(request);

        BaseResponse baseResponse = CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message("Successfully create transaction")
                .data(transaksi)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(baseResponse);
    }

    @GetMapping("/siswa/{siswaId}")
    public ResponseEntity<BaseResponse> getTransaksiBySiswaId(@PathVariable String siswaId) {

        List<TransaksiSiswaResponse> transaksiList = transaksiService.getAllByIdSiswa(siswaId);

        BaseResponse response = CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message("Successfully get all transaction siswa")
                .data(transaksiList)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(path = ApiUrl.PATH_VAR_ID)
    public ResponseEntity<BaseResponse> getTransactionById (@PathVariable String id) {
        Transaksi transaksi = transaksiService.getById(id);

        TransaksiByIdResponse transaksiByIdResponse = TransaksiByIdResponse.builder()
                .id(transaksi.getId())
                .nama(transaksi.getSiswa().getNama())
                .nis(transaksi.getSiswa().getNis())
                .email(transaksi.getSiswa().getEmail())
                .jumlahBayar(transaksi.getJumlahBayar())
                .golongan(transaksi.getSiswa().getGolongan().getGolongan())
                .transactionStatus(transaksi.getPembayaran().getTransactionStatus())
                .transDate(transaksi.getTransDate())
                .build();

        BaseResponse response = CommonResponse.<TransaksiByIdResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Successfully get data transaction")
                .data(transaksiByIdResponse)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @PostMapping(path = "/status")
    public ResponseEntity<BaseResponse> updateStatus(
            @RequestBody Map<String, Object> request
    ) {
        UpdateTransaksiPembayaranStatusRequest updateStatus = UpdateTransaksiPembayaranStatusRequest.builder()
                .orderId(request.get("order_id").toString())
                .transactionStatus(request.get("transaction_status").toString())
                .build();

        transaksiService.updateStatus(updateStatus);

        BaseResponse response = CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message("Successfully update status")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}