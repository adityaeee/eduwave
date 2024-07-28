package com.codex.eduwave.controller;

import com.codex.eduwave.constant.ApiUrl;
import com.codex.eduwave.entity.Golongan;
import com.codex.eduwave.model.request.GolonganRequest;
import com.codex.eduwave.model.request.UpdateGolonganRequest;
import com.codex.eduwave.model.response.BaseResponse;
import com.codex.eduwave.model.response.CommonResponse;
import com.codex.eduwave.model.response.CommonResponseWithPage;
import com.codex.eduwave.service.intrface.GolonganService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<BaseResponse> getAll(){
        List<Golongan> golonganList = golonganService.getAll();

        BaseResponse response = CommonResponseWithPage.builder()
                .statusCode(HttpStatus.OK.value())
                .message("successfully get data")
                .data(golonganList)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<BaseResponse> update(
            @RequestBody UpdateGolonganRequest request
            ){

        Golongan golongan = golonganService.update(request);

        BaseResponse response = CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message("successfully update data")
                .data(golongan)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = ApiUrl.PATH_VAR_ID)
    public ResponseEntity<BaseResponse> update(
            @PathVariable String id
    ){
        golonganService.delete(id);

        BaseResponse response = CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message("successfully delete data")
                .build();
        return ResponseEntity.ok(response);
    }
}
