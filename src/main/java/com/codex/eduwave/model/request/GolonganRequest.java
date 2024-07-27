package com.codex.eduwave.model.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GolonganRequest {

    @NotBlank(message = "golongan is mandatory and cannot be blank")
    private String golongan;

    @NotNull(message = "spp is mandatory and cannot be null")
    private Integer spp;

    @NotBlank(message = "sekolahId is mandatory and cannot be blank")
    private String sekolahId;
}
