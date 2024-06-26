package com.picpaychallenge.common;

public class ExceptionWithCode extends RuntimeException {
  private String code;

  public ExceptionWithCode(String message, String code) {
    super(message);
    this.code = code;
  }

  public String getCode() {
    return code;
  }
}
