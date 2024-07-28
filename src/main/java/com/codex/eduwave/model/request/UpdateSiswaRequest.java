package com.codex.eduwave.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateSiswaRequest {

    @NotBlank(message = "Siswa id is mandatory and cannot be blank")
    private String id;

    @NotBlank(message = "nama is mandatory and cannot be blank")
    private String nama;

    @NotBlank(message = "NIS is mandatory and cannot be blank")
    @Pattern(regexp = "^[0-9]+$",message = "NIS must containt only numbers")
    private String nis;

    @NotBlank(message = "Email is mandatory and cannot be blank")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "No hp is mandatory and cannot be blank")
    private String noHp;

    @NotBlank(message = "No hp ortu is mandatory and cannot be blank")
    private String noHpOrtu;

    private String alamat;

    @NotBlank(message = "golonganId is mandatory and cannot be blank")
    private String golonganId;


}
