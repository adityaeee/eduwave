package com.codex.eduwave.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PembayaranDetailRequest {
    @JsonProperty("order_id")
    private String orderId;

    @JsonProperty("gross_amount")
    private Integer amount;

}
