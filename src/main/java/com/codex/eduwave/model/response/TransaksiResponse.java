package com.codex.eduwave.model.response;

import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransaksiResponse {
    private String id;
    private Date transDate;
    private SiswaResponse siswa;
    private Integer jumlahBayar;
    private PembayaranResponse pembayaran;

}
