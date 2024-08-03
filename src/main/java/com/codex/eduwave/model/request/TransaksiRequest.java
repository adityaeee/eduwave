package com.codex.eduwave.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransaksiRequest {
    private String sekolahId;
    private String nis;
    private Integer jumlahBayar;
}
