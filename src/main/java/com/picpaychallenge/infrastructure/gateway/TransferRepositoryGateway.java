package com.picpaychallenge.infrastructure.gateway;

import java.util.Optional;
import java.util.UUID;

import com.picpaychallenge.application.dto.TransferDTO;
import com.picpaychallenge.application.repository.ICreateTransferRepository;
import com.picpaychallenge.domain.entity.TransferEntity;
import com.picpaychallenge.domain.valueobjects.Money;
import com.picpaychallenge.infrastructure.mapper.TransferMapper;
import com.picpaychallenge.infrastructure.model.TransferModel;
import com.picpaychallenge.infrastructure.model.UserModel;
import com.picpaychallenge.infrastructure.repository.TransferRepository;
import com.picpaychallenge.infrastructure.repository.UserRepository;

public class TransferRepositoryGateway implements ICreateTransferRepository {
  private final TransferRepository transferRepository;
  private final UserRepository userRepository;

  public TransferRepositoryGateway(TransferRepository transferRepository, UserRepository userRepository) {
    this.transferRepository = transferRepository;
    this.userRepository = userRepository;
  }

  @Override
  public TransferDTO create(TransferEntity transfer) {
    Optional<UserModel> payerUser = userRepository.findById(transfer.getPayer().getId());
    Optional<UserModel> payeeUser = userRepository.findById(transfer.getPayee().getId());

    if (payerUser.isEmpty())
      throw new RuntimeException("User not found.");

    if (payeeUser.isEmpty())
      throw new RuntimeException("User not found.");

    Money transferAmount = transfer.getMoney();

    payerUser.get().setBalance(payerUser.get().getBalance() - transferAmount.getCents());
    userRepository.save(payerUser.get());

    payeeUser.get().setBalance(payeeUser.get().getBalance() + transferAmount.getCents());
    userRepository.save(payeeUser.get());

    TransferModel model = TransferMapper.businessEntityToModel(UUID.randomUUID(), transfer);
    TransferModel savedModel = this.transferRepository.save(model);
    TransferDTO dto = TransferMapper.modelToBusinessDTO(savedModel);
    return dto;
  }
}
