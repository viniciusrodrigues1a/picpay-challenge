package com.picpaychallenge.infrastructure.dto;

import java.util.UUID;

public class CreateTransferControllerDTO {
  public static record Request(UUID payerId, UUID payeeId, int cents, String currency) {
  }

  public static record Response(UUID id) {
  }
}
