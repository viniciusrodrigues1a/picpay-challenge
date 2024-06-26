package com.picpaychallenge.infrastructure.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.picpaychallenge.common.ExceptionWithCode;
import com.picpaychallenge.infrastructure.dto.ExceptionDTO;

@RestControllerAdvice
public class ControllerExceptionHandler {
  @ExceptionHandler(ExceptionWithCode.class)
  public ResponseEntity<ExceptionDTO> handleExceptionWithCode(ExceptionWithCode exceptionWithCode) {
    ExceptionDTO exceptionDTO = new ExceptionDTO(exceptionWithCode.getMessage(), exceptionWithCode.getCode());
    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    switch (exceptionWithCode.getCode()) {
      case "INVALID_EMAIL":
        status = HttpStatus.BAD_REQUEST;
        break;
      case "CURRENCIES_MUST_MATCH":
        status = HttpStatus.BAD_REQUEST;
        break;
      case "MERCHANT_CANT_TRANSFER":
        status = HttpStatus.FORBIDDEN;
        break;
      case "INSUFFICIENT_BALANCE":
        status = HttpStatus.FORBIDDEN;
        break;
      case "EMAIL_TAKEN":
        status = HttpStatus.CONFLICT;
        break;
      case "DOCUMENT_TAKEN":
        status = HttpStatus.CONFLICT;
        break;
      case "UNAUTHORIZED_TRANSFER":
        status = HttpStatus.UNAUTHORIZED;
        break;
    }

    return new ResponseEntity<ExceptionDTO>(exceptionDTO, status);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ExceptionDTO> handleException(Exception exception) {
    ExceptionDTO exceptionDTO = new ExceptionDTO("Internal server error", "INTERNAL_SERVER_ERROR");
    return new ResponseEntity<>(exceptionDTO, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
