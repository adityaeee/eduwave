package com.codex.eduwave.controller;

import com.codex.eduwave.constant.ApiUrl;
import com.codex.eduwave.entity.Siswa;
import com.codex.eduwave.model.request.SiswaRequest;
import com.codex.eduwave.model.response.BaseResponse;
import com.codex.eduwave.model.response.CommonResponse;
import com.codex.eduwave.service.intrface.SiswaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiUrl.SISWA_API)
public class SiswaController {

    private final SiswaService siswaService;

    @PostMapping
    public ResponseEntity<BaseResponse> createSiswa(@RequestBody SiswaRequest request){

        Siswa siswa = siswaService.create(request);
        BaseResponse response = CommonResponse.<Siswa>builder()
                .statusCode(HttpStatus.OK.value())
                .message("successfully add data")
                .data(siswa)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = ApiUrl.PATH_VAR_ID)
    public ResponseEntity<BaseResponse> getById(
            @PathVariable String id
    ){
        Siswa siswa = siswaService.getById(id);
        BaseResponse response = CommonResponse.<Siswa>builder()
                .statusCode(HttpStatus.OK.value())
                .message("successfully add data")
                .data(siswa)
                .build();
        return ResponseEntity.ok(response);
    }
}
