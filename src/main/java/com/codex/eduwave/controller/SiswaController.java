package com.codex.eduwave.controller;

import com.codex.eduwave.constant.ApiUrl;
import com.codex.eduwave.entity.Siswa;
import com.codex.eduwave.model.request.SiswaRequest;
import com.codex.eduwave.model.request.UpdateSiswaRequest;
import com.codex.eduwave.model.response.BaseResponse;
import com.codex.eduwave.model.response.CommonResponse;
import com.codex.eduwave.model.response.SiswaResponse;
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

        BaseResponse response = CommonResponse.<SiswaResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("successfully add data")
                .data(siswaResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = ApiUrl.PATH_VAR_ID)
    public ResponseEntity<BaseResponse> getById(
            @PathVariable String id
    ){
        Siswa siswa = siswaService.getById(id);

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

        BaseResponse response = CommonResponse.<SiswaResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("successfully add data")
                .data(siswaResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<BaseResponse> updateSiswa (@RequestBody UpdateSiswaRequest request) {
        Siswa siswa = siswaService.update(request);

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

        BaseResponse response = CommonResponse.<SiswaResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("successfully update data siswa")
                .data(siswaResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = ApiUrl.PATH_VAR_ID)
    public ResponseEntity<BaseResponse> inActiveSiswa (@PathVariable String id){
        siswaService.getById(id);
        BaseResponse response = CommonResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Student data that was successfully changed to be inactive")
                .data(String.format("Students with ID %S are inactive", id))
                .build();
        return ResponseEntity.ok(response);
    }
}
