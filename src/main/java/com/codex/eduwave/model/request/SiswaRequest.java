package com.codex.eduwave.model.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SiswaRequest {
    private String nama;
    private String nis;
    private String email;
    private String noHp;
    private String noHpOrtu;
    private String alamat;
    private Integer tagihan;
    private String golonganId;


}
