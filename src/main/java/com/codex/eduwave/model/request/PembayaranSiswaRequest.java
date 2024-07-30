package com.codex.eduwave.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PembayaranSiswaRequest {

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String nis;

    @JsonProperty("email")
    private String email;

    @JsonProperty("phone")
    private String phone;
}
