package com.codex.eduwave.model.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    @NotBlank(message = "Sekolah is mandatory and cannot be blank")
    private String sekolah;

    @NotBlank(message = "Email is mandatory and cannot be blank")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Phone Number is mandatory and cannot be blank")
    private String noHp;

    @Pattern(regexp = "^[0-9]+$",message = "NPSN must containt only numbers")
    @NotBlank(message = "NPSN is mandatory and cannot be blank")
    private String npsn;

    private String password;

    private MultipartFile logo;

    private String createdBy;
}
