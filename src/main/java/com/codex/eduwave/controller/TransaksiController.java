package com.codex.eduwave.controller;

import com.codex.eduwave.constant.ApiUrl;
import com.codex.eduwave.entity.Transaksi;
import com.codex.eduwave.model.request.TransaksiRequest;
import com.codex.eduwave.model.response.BaseResponse;
import com.codex.eduwave.model.response.CommonResponse;
import com.codex.eduwave.model.response.TransaksiResponse;
import com.codex.eduwave.model.response.TransaksiSiswaResponse;
import com.codex.eduwave.service.intrface.TransaksiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiUrl.TRANSAKSI_API)
public class TransaksiController {
    private final TransaksiService transaksiService;

    @PostMapping
    public ResponseEntity<BaseResponse> createNewTransaksi (@RequestBody TransaksiRequest request) {
        TransaksiResponse transaksi = transaksiService.create(request);

        BaseResponse baseResponse = CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message("Successfully create transaction")
                .data(transaksi)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(baseResponse);
    }

    @GetMapping("{siswaId}")
    public ResponseEntity<BaseResponse> getTransaksiBySiswaId(@PathVariable String siswaId) {

        List<TransaksiSiswaResponse> transaksiList = transaksiService.getAllByIdSiswa(siswaId);

        BaseResponse response = CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message("Successfully get all transaction siswa")
                .data(transaksiList)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
