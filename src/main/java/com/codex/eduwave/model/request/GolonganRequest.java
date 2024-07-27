package com.codex.eduwave.model.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GolonganRequest {

    private String golongan;

    private Integer spp;

    private String sekolahId;
}
