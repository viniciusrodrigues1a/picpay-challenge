package com.picpaychallenge.application.dto;

import com.picpaychallenge.domain.valueobjects.UserType;

public record CreateUserDTO(String fullName, String email, String password, String document, UserType type, int cents) {
}
