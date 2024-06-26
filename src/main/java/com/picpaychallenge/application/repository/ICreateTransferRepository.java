package com.picpaychallenge.application.repository;

import com.picpaychallenge.application.dto.TransferDTO;
import com.picpaychallenge.domain.entity.TransferEntity;

public interface ICreateTransferRepository {
  public TransferDTO create(TransferEntity transfer);
}
