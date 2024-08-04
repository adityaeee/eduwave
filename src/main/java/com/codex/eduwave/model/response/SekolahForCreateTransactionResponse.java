package com.codex.eduwave.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SekolahForCreateTransactionResponse {
    private String id;
    private String sekolah;
    private String logo;
}
