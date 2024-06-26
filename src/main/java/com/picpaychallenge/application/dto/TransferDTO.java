package com.picpaychallenge.application.dto;

import java.util.UUID;

import com.picpaychallenge.domain.valueobjects.Money;

public record TransferDTO(UUID id, UserDTO payer, UserDTO payee, Money money) {
}
