package com.codex.eduwave.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransaksiRequest {
    private String nis;
    private Integer jumlahBayar;
}
