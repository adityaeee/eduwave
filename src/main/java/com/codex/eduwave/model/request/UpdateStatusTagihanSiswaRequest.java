package com.codex.eduwave.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateStatusTagihanSiswaRequest {
    private String siswaId;
    private Integer jumlahBayar;
}
