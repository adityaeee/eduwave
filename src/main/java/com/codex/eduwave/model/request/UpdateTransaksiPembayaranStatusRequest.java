package com.codex.eduwave.model.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateTransaksiPembayaranStatusRequest {
    private String orderId;
    private String transactionStatus;
}