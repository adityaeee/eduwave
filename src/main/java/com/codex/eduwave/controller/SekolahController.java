package com.codex.eduwave.controller;

import com.codex.eduwave.constant.ApiUrl;
import com.codex.eduwave.entity.Sekolah;
import com.codex.eduwave.model.request.SearchSekolahRequest;
import com.codex.eduwave.model.request.SekolahRequest;
import com.codex.eduwave.model.request.UpdateSekolahRequest;
import com.codex.eduwave.model.response.*;
import com.codex.eduwave.service.intrface.SekolahService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiUrl.SEKOLAH_API)
public class SekolahController {
    private final SekolahService sekolahService;
    private final ObjectMapper objectMapper;


    @GetMapping
    public ResponseEntity<BaseResponse> getAllDataSekolah(

            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(name = "direction", defaultValue = "asc") String direction,
            @RequestParam(name = "sekolah",required = false) String sekolahParam,
            @RequestParam(name = "npsn",required = false) String npsn

    ) {

        SearchSekolahRequest searchSekolahRequest = SearchSekolahRequest.builder()
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .direction(direction)
                .sekolah(sekolahParam)
                .npsn(npsn)
                .build();
        Page<Sekolah> allSekolah = sekolahService.getAllSekolah(searchSekolahRequest);

        PagingResponse pagingResponse = PagingResponse.builder()
                .totalPages(allSekolah.getTotalPages())
                .totalElements(allSekolah.getTotalElements())
                .page(allSekolah.getPageable().getPageNumber()+1)
                .size(allSekolah.getPageable().getPageSize())
                .hasNext(allSekolah.hasNext())
                .hasPrevious(allSekolah.hasPrevious())
                .build();

        List<SekolahResponse> listSekolahResponse = allSekolah.getContent().stream().map(
                sekolah -> {
                    return SekolahResponse.builder()
                            .id(sekolah.getId())
                            .sekolah(sekolah.getSekolah())
                            .email(sekolah.getEmail())
                            .noHp(sekolah.getNoHp())
                            .npsn(sekolah.getNpsn())
                            .logo(sekolah.getLogo().getUrl())
                            .golonganSekolah(sekolah.getGolonganSekolah())
                            .createdBy(sekolah.getCreatedBy())
                            .build();
                }
        ).toList();

        BaseResponse response = CommonResponseWithPage.builder()
                .statusCode(HttpStatus.OK.value())
                .message("Successfully get data sekolah")
                .data(listSekolahResponse)
                .paging(pagingResponse)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @PostMapping
    public ResponseEntity<BaseResponse> create(
            @RequestPart(name = "logo")MultipartFile logo,
            @RequestPart(name = "sekolah_request") String jsonSekolahRequest
            ){
        CommonResponse.CommonResponseBuilder<SekolahResponse> sekolahBuilder = CommonResponse.builder();
        try{
            SekolahRequest sekolahRequest = objectMapper.readValue(jsonSekolahRequest, new TypeReference<SekolahRequest>() {});
            sekolahRequest.setLogo(logo);

            Sekolah sekolah = sekolahService.createSekolah(sekolahRequest);

            SekolahResponse sekolahResponse = SekolahResponse.builder()
                    .id(sekolah.getId())
                    .sekolah(sekolah.getSekolah())
                    .email(sekolah.getEmail())
                    .noHp(sekolah.getNoHp())
                    .npsn(sekolah.getNpsn())
                    .logo(sekolah.getLogo().getUrl())
                    .golonganSekolah(sekolah.getGolonganSekolah())
                    .createdBy(sekolah.getCreatedBy())
                    .build();

            sekolahBuilder.statusCode(HttpStatus.OK.value());
            sekolahBuilder.message("successfully add data");
            sekolahBuilder.data(sekolahResponse);
            return ResponseEntity.status(HttpStatus.OK).body(sekolahBuilder.build());


        }catch (Exception e){
            sekolahBuilder.message(e.toString());
            sekolahBuilder.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(sekolahBuilder.build());

        }
    }

    @GetMapping(path = ApiUrl.PATH_VAR_ID)
    public ResponseEntity<BaseResponse> getById(@PathVariable String id){
        Sekolah sekolah = sekolahService.getById(id);

        SekolahResponse sekolahResponse = SekolahResponse.builder()
                .id(sekolah.getId())
                .sekolah(sekolah.getSekolah())
                .email(sekolah.getEmail())
                .noHp(sekolah.getNoHp())
                .npsn(sekolah.getNpsn())
                .logo(sekolah.getLogo().getUrl())
                .golonganSekolah(sekolah.getGolonganSekolah())
                .createdBy(sekolah.getCreatedBy())
                .build();
        CommonResponse<SekolahResponse> response = CommonResponse.<SekolahResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("successfully get data")
                .data(sekolahResponse)
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<BaseResponse> update(
            @RequestPart(name = "logo", required = false) MultipartFile logo,
            @RequestPart(name = "sekolah_request") String jsonSekolahRequest
    ){
        CommonResponse.CommonResponseBuilder<SekolahResponse> sekolahBuilder = CommonResponse.builder();
        try{
            UpdateSekolahRequest sekolahRequest = objectMapper.readValue(jsonSekolahRequest, new TypeReference<UpdateSekolahRequest>() {});
            sekolahRequest.setLogo(logo);

            Sekolah sekolah = sekolahService.update(sekolahRequest);

            SekolahResponse sekolahResponse = SekolahResponse.builder()
                    .id(sekolah.getId())
                    .sekolah(sekolah.getSekolah())
                    .email(sekolah.getEmail())
                    .noHp(sekolah.getNoHp())
                    .npsn(sekolah.getNpsn())
                    .logo(sekolah.getLogo().getUrl())
                    .golonganSekolah(sekolah.getGolonganSekolah())
                    .createdBy(sekolah.getCreatedBy())
                    .build();

            sekolahBuilder.statusCode(HttpStatus.OK.value());
            sekolahBuilder.message("successfully update data");
            sekolahBuilder.data(sekolahResponse);
            return ResponseEntity.status(HttpStatus.OK).body(sekolahBuilder.build());


        }catch (Exception e){
            sekolahBuilder.message(e.toString());
            sekolahBuilder.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(sekolahBuilder.build());

        }
    }

    @DeleteMapping(path = ApiUrl.PATH_VAR_ID)
    public ResponseEntity<BaseResponse> deleteSekolahById(@PathVariable String id){
        sekolahService.delete(id);

        CommonResponse<SekolahResponse> response = CommonResponse.<SekolahResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("successfully delete data sekolah")
                .build();

        return ResponseEntity.ok(response);
    }
}
