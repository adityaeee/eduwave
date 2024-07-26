package com.codex.eduwave.controller;

import com.codex.eduwave.constant.ApiUrl;
import com.codex.eduwave.entity.Golongan;
import com.codex.eduwave.model.request.GolonganRequest;
import com.codex.eduwave.model.response.BaseResponse;
import com.codex.eduwave.model.response.CommonResponse;
import com.codex.eduwave.service.intrface.GolonganService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiUrl.GOLONGAN_API)
public class GolonganController {
    private final GolonganService golonganService;

    @PostMapping
    public ResponseEntity<BaseResponse> createGolongan(
            @RequestBody GolonganRequest request
            ){
        Golongan golongan = golonganService.create(request);
        BaseResponse response= CommonResponse.<Golongan>builder()
                .statusCode(HttpStatus.OK.value())
                .message("successfully add data")
                .data(golongan)
                .build();

        return ResponseEntity.ok(response);

    }

    @GetMapping(path = ApiUrl.PATH_VAR_ID)
    public  ResponseEntity<BaseResponse> getById(
            @PathVariable String id
    ){
        Golongan golongan = golonganService.getById(id);
        BaseResponse response= CommonResponse.<Golongan>builder()
                .statusCode(HttpStatus.OK.value())
                .message("successfully add data")
                .data(golongan)
                .build();

        return ResponseEntity.ok(response);

    }
}
