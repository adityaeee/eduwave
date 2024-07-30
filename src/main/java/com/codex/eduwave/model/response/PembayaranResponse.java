package com.codex.eduwave.model.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PembayaranResponse {
    private String id;
    private String token;
    private String redirectUrl;
    private String transactionStatus;
}

