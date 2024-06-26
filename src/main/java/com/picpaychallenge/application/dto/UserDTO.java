package com.picpaychallenge.application.dto;

import java.util.UUID;

import com.picpaychallenge.domain.valueobjects.DocumentType;
import com.picpaychallenge.domain.valueobjects.UserType;

public record UserDTO(UUID id, String fullName, String email, String document, DocumentType documentType,
    UserType type, int cents) {
}
