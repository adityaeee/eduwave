package com.codex.eduwave.controller;

import com.codex.eduwave.constant.ApiUrl;
import com.codex.eduwave.model.request.TransaksiRequest;
import com.codex.eduwave.model.response.BaseResponse;
import com.codex.eduwave.model.response.CommonResponse;
import com.codex.eduwave.model.response.TransaksiResponse;
import com.codex.eduwave.service.intrface.TransaksiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
