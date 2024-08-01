package com.codex.eduwave.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransaksiSiswaResponse {
    private String id;

    @JsonProperty("siswa_id")
    private String siswaId;

    private String nis;

    @JsonProperty("nama_siswa")
    private String namaSiswa;

    private String golongan;

    @JsonProperty("jumlah_pembayaran")
    private Integer jumlahPembayaran;

    @JsonProperty("status_pembayaran")
    private String statusPembayaran;

    @JsonProperty("tanggal_transaksi")
    private Date tanggalTransaksi;


}
