package com.codex.eduwave.controller;

import com.codex.eduwave.constant.ApiUrl;
import com.codex.eduwave.entity.Sekolah;
import com.codex.eduwave.model.request.SekolahRequest;
import com.codex.eduwave.model.response.BaseResponse;
import com.codex.eduwave.model.response.CommonResponse;
import com.codex.eduwave.model.response.SekolahResponse;
import com.codex.eduwave.service.intrface.SekolahService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiUrl.SEKOLAH_API)
public class SekolahController {
    private final SekolahService sekolahService;
    private final ObjectMapper objectMapper;


    @PostMapping
    public ResponseEntity<BaseResponse> create(
            @RequestPart(name = "logo")MultipartFile logo,
            @RequestPart(name = "sekolah_request") String jsonSekolahRequest
            ){
        CommonResponse.CommonResponseBuilder<SekolahResponse> sekolahBuilder = CommonResponse.builder();
        try{
            SekolahRequest sekolahRequest = objectMapper.readValue(jsonSekolahRequest, new TypeReference<SekolahRequest>() {
            });
            sekolahRequest.setLogo(logo);

            SekolahResponse sekolah = sekolahService.createSekolah(sekolahRequest);
            sekolahBuilder.statusCode(HttpStatus.OK.value());
            sekolahBuilder.message("successfully add data");
            sekolahBuilder.data(sekolah);
            return ResponseEntity.status(HttpStatus.OK).body(sekolahBuilder.build());


        }catch (Exception e){
            sekolahBuilder.message(e.toString());
            sekolahBuilder.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(sekolahBuilder.build());

        }
    }

    @GetMapping(path = ApiUrl.PATH_VAR_ID)
    public ResponseEntity<BaseResponse> getById(@PathVariable String id){
        SekolahResponse sekolah = sekolahService.getById(id);
        CommonResponse<SekolahResponse> response = CommonResponse.<SekolahResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("successfully get data")
                .data(sekolah)
                .build();

        return ResponseEntity.ok(response);
    }
}
