package com.codex.eduwave.model.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SekolahRequest {
    private  String name;

    private String email;

    private String noHp;

    private String npsn;

    private MultipartFile suratIzinOperasional;

    private MultipartFile sertifikatAkreditasi;

    private MultipartFile logo;
}
