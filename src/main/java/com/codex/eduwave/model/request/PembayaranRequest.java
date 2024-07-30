package com.codex.eduwave.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PembayaranRequest {
    @JsonProperty("transaction_details")
    private PembayaranDetailRequest pembayaranDetail;

    @JsonProperty("item_details")
    private List<PembayaranJenisGolonganRequest> pembayaranJenisGolongan;

    @JsonProperty("enabled_payments")
    private List<String> pembayaranMethod; //shoppeepay, gopay, dana

    @JsonProperty("customer_details")
    private PembayaranSiswaRequest siswa;

}
