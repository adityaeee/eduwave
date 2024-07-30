package com.codex.eduwave.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GolonganResponse {

    private String id;

    private String gologan;

    private Integer spp;

    private Date createdAt;

    private Date updateAt;


}
