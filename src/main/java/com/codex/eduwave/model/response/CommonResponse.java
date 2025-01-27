package com.codex.eduwave.model.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommonResponse<T> extends BaseResponse {
    private Integer statusCode;
    private String message;
    private T data;


}
