package com.codex.eduwave.model.response;


import com.codex.eduwave.entity.Golongan;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SekolahResponse {
    private String id;

    private String sekolah;

    private String email;

    @JsonProperty("no_hp")
    private String noHp;

    private String npsn;

    private String logo;

    @JsonProperty("golongan_sekolah")
    private List<Golongan> golonganSekolah;

    private List<String> role;

    @JsonProperty("created_by")
    private String createdBy;

}
