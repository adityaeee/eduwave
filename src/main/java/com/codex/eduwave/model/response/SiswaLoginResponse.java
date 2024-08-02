package com.codex.eduwave.model.response;

import com.codex.eduwave.constant.StatusSPP;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SiswaLoginResponse {
    private String id;

    private String nama;

    @JsonProperty("NIS")
    private String nis;

    private String email;

    @JsonProperty("no_hp")
    private String noHp;

    @JsonProperty("no_hp_ortu")
    private String noHpOrtu;

    private String alamat;

    @Enumerated(EnumType.STRING)
    private StatusSPP status;

    private Integer tagihan;

    @JsonProperty("is_active")
    private Boolean isActive;

    private String sekolah;

    private String urlLogo;

    @JsonProperty("golongan")
    private String golongan;

    @JsonProperty("spp")
    private Integer spp;

    @JsonProperty("created_at")
    private Date createdAt;

    @JsonProperty("updated_at")
    private Date updatedAt;
}
