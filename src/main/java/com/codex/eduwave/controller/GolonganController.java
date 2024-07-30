package com.codex.eduwave.controller;

import com.codex.eduwave.constant.ApiUrl;
import com.codex.eduwave.entity.Golongan;
import com.codex.eduwave.model.request.GolonganRequest;
import com.codex.eduwave.model.request.SearchGolonganRequest;
import com.codex.eduwave.model.request.UpdateGolonganRequest;
import com.codex.eduwave.model.response.BaseResponse;
import com.codex.eduwave.model.response.CommonResponse;
import com.codex.eduwave.model.response.CommonResponseWithPage;
import com.codex.eduwave.model.response.GolonganResponse;
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

        GolonganResponse golonganResponse = GolonganResponse.builder()
                .id(golongan.getId())
                .gologan(golongan.getGolongan())
                .spp(golongan.getSpp())
                .createdAt(golongan.getCreatedAt())
                .updateAt(golongan.getUpdatedAt())
                .build();

        BaseResponse response= CommonResponse.<GolonganResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("successfully add data")
                .data(golonganResponse)
                .build();

        return ResponseEntity.ok(response);

    }

    @GetMapping(path = ApiUrl.PATH_VAR_ID)
    public  ResponseEntity<BaseResponse> getById(
            @PathVariable String id
    ){
        Golongan golongan = golonganService.getById(id);


        GolonganResponse golonganResponse = GolonganResponse.builder()
                .id(golongan.getId())
                .gologan(golongan.getGolongan())
                .spp(golongan.getSpp())
                .createdAt(golongan.getCreatedAt())
                .updateAt(golongan.getUpdatedAt())
                .build();

        BaseResponse response= CommonResponse.<GolonganResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("successfully add data")
                .data(golonganResponse)
                .build();

        return ResponseEntity.ok(response);

    }

    @GetMapping
    public ResponseEntity<BaseResponse> getAll(

            @RequestParam(name = "golongan",required = false) String golongan,
            @RequestParam(name = "spp", required = false) Integer spp
    ){
        SearchGolonganRequest searchGolonganRequest = SearchGolonganRequest.builder()
                .name(golongan)
                .spp(spp)
                .build();
        List<Golongan> golonganList = golonganService.getAll(searchGolonganRequest);

        List<GolonganResponse> listGolonganResponse = golonganList.stream().map(

                singleGolongan -> {
                    return GolonganResponse.builder()
                            .id(singleGolongan.getId())
                            .gologan(singleGolongan.getGolongan())
                            .spp(singleGolongan.getSpp())
                            .createdAt(singleGolongan.getCreatedAt())
                            .updateAt(singleGolongan.getUpdatedAt())
                            .build();
                }
        ).toList();

        BaseResponse response = CommonResponseWithPage.builder()
                .statusCode(HttpStatus.OK.value())
                .message("successfully get data")
                .data(listGolonganResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<BaseResponse> update(
            @RequestBody UpdateGolonganRequest request
            ){

        Golongan golongan = golonganService.update(request);


        GolonganResponse golonganResponse = GolonganResponse.builder()
                .id(golongan.getId())
                .gologan(golongan.getGolongan())
                .spp(golongan.getSpp())
                .createdAt(golongan.getCreatedAt())
                .updateAt(golongan.getUpdatedAt())
                .build();

        BaseResponse response = CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message("successfully update data")
                .data(golonganResponse)
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
