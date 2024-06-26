package com.picpaychallenge.infrastructure.dto;

import com.picpaychallenge.domain.valueobjects.UserType;

public class CreateUserControllerDTO {
  public static record Request(String fullName, String email, String password, String document,
      UserType type, int cents) {
  }

  public static record Response(String fullName, String email, UserType type) {
  }
}
