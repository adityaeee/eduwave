package com.codex.eduwave.model.response;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonResponseWithPage<T> extends BaseResponse{

    private Integer statusCode;
    private String message;
    private T data;
    private PagingResponse paging;


}
