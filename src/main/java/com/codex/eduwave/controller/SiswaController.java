package com.codex.eduwave.controller;

import com.codex.eduwave.constant.ApiUrl;
import com.codex.eduwave.constant.StatusSPP;
import com.codex.eduwave.entity.Siswa;
import com.codex.eduwave.model.request.*;
import com.codex.eduwave.model.response.*;
import com.codex.eduwave.service.intrface.SiswaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiUrl.SISWA_API)
public class SiswaController {

    private final SiswaService siswaService;

    @GetMapping
    public ResponseEntity<BaseResponse> getAllDataSiswaWithSekolahId(
            @RequestParam(name = "nama", required = false) String nama,
            @RequestParam(name = "nis", required = false) String nis,
            @RequestParam(name = "tagihan", required = false) Integer tagihan,
            @RequestParam(name = "isActive", required = false) Boolean isActive,
            @RequestParam(name = "status", required = false) StatusSPP status,
            @RequestParam(name = "golongan", required = false) String golongan,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "sortBy", defaultValue = "nama") String sortBy,
            @RequestParam(name = "direction", defaultValue = "asc") String direction
    ) {

        SearchSiswaRequest request = SearchSiswaRequest.builder()
                .nama(nama)
                .nis(nis)
                .status(status)
                .tagihan(tagihan)
                .golongan(golongan)
                .isActive(isActive)
                .size(size)
                .page(page)
                .sortBy(sortBy)
                .direction(direction)
                .build();

        Page<Siswa> allSiswa = siswaService.getAll(request);

        PagingResponse pagingResponse = PagingResponse.builder()
                .totalPages(allSiswa.getTotalPages())
                .totalElements(allSiswa.getTotalElements())
                .page(allSiswa.getPageable().getPageNumber())
                .size(allSiswa.getSize())
                .hasNext(allSiswa.hasNext())
                .hasPrevious(allSiswa.hasPrevious())
                .build();

        List<SiswaResponse> listSiswaResponse = allSiswa.getContent().stream().map(
                siswa -> {
                    return SiswaResponse.builder()
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
                }
        ).toList();

        BaseResponse response = CommonResponseWithPage.builder()
                .statusCode(HttpStatus.OK.value())
                .message("Successfully get all data siswa")
                .paging(pagingResponse)
                .data(listSiswaResponse)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

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
        siswaService.inActive(id);
        BaseResponse response = CommonResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Student data that was successfully changed to be inactive")
                .data(String.format("Students with ID %S are inactive", id))
                .build();
        return ResponseEntity.ok(response);
    }



    @PostMapping(path = "/reset")
    public ResponseEntity<BaseResponse> resetSiswaStatus(
            @RequestBody UpdateStatusSiswaBulk request
    ){

        List<Siswa> list = request.getSiswaId().stream().map(
                siswa -> {

                    return siswaService.getById(siswa);
                }
        ).toList();

        List<Siswa> siswaList = siswaService.updateStatusBulk(list);

        BaseResponse response = CommonResponseWithPage.builder()
                .statusCode(HttpStatus.OK.value())
                .message("successfully update status")
                .build();

        return ResponseEntity.ok(response);

    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponse> loginSiswa(@RequestBody LoginSiswaRequest request){

        Siswa siswa = siswaService.loginSiswa(request);

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
}
