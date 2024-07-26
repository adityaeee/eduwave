package com.codex.eduwave.controller;

import com.codex.eduwave.model.response.BaseResponse;
import com.codex.eduwave.model.response.CommonResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ErrorController {

//    handler exception
    @ExceptionHandler({ResponseStatusException.class})
    public ResponseEntity<BaseResponse> responseResponseEntityStatusException (ResponseStatusException exception) {
        CommonResponse<?>response = CommonResponse.builder()
                .statusCode(exception.getStatusCode().value())
                .message(exception.getMessage())
                .build();

        return ResponseEntity
                .status(exception.getStatusCode())
                .body(response);
    }

//      error from database
    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<BaseResponse> dataIntegrityViolationException (DataIntegrityViolationException e) {
        CommonResponse.CommonResponseBuilder<Object> builder =CommonResponse.builder();
        HttpStatus httpStatus;

        if(e.getMessage().contains("foreign key constraint")) {
            builder.statusCode(HttpStatus.BAD_REQUEST.value());
            builder.message("cannot delete data because other table depend on it");
            httpStatus = HttpStatus.BAD_REQUEST;
        } else if(e.getMessage().contains("unique constraint") || e.getMessage().contains("Duplicate entry")) {
            builder.statusCode(HttpStatus.CONFLICT.value());
            builder.message("Data already exist");
            httpStatus = HttpStatus.CONFLICT;
        } else {
            builder.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            builder.message("Internal Server Error");
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }


        return ResponseEntity
                .status(httpStatus)
                .body(builder.build());
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<BaseResponse> constraintViolationException (ConstraintViolationException e) {
        CommonResponse<?> response = CommonResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .build();

        return  ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<BaseResponse> baseResponseResponseEntity (BadCredentialsException e) {
        CommonResponse<?> response = CommonResponse.builder()
                .statusCode(HttpStatus.FORBIDDEN.value())
                .message("something wrong, please check manually username or password and login again")
                .data(e.getLocalizedMessage())
                .build();

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(response);
    }


}
