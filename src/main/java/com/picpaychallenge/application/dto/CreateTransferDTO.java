package com.picpaychallenge.application.dto;

public record CreateTransferDTO(UserDTO payerDTO, UserDTO payeeDTO, int cents, String currency) {
}
