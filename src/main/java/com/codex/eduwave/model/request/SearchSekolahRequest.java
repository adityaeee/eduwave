package com.codex.eduwave.model.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchSekolahRequest {
    private Integer page;
    private Integer size;
    private String sortBy;
    private String direction;
    private String sekolah;
    private String npsn;
}
