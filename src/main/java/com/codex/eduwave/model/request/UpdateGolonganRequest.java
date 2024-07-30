package com.codex.eduwave.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateGolonganRequest {
    private String id;
    private String golongan;
    private Integer spp;
}