package com.codex.eduwave.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransaksiByIdResponse {
    private String id;
    private Date transDate;
    private String nama;
    @JsonProperty("NIS")
    private String nis;
    private String email;
    private String golongan;
    private Integer jumlahBayar;
    private String transactionStatus;
}
