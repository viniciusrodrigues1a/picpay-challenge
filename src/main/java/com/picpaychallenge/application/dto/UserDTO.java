package com.picpaychallenge.application.dto;

import com.picpaychallenge.domain.valueobjects.DocumentType;
import com.picpaychallenge.domain.valueobjects.UserType;

public record UserDTO(String fullName, String email, String document, DocumentType documentType,
    UserType type) {
}
