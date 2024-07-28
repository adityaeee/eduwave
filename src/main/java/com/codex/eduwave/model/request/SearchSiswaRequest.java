package com.codex.eduwave.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchSiswaRequest {
    private String nama;
    private String nis;
    private Integer tagihan;
    private Boolean isActive;
    private String status;
    private String sekolahId;

    private Integer page;
    private Integer size;

    private String sortBy;
    private String direction;
}
