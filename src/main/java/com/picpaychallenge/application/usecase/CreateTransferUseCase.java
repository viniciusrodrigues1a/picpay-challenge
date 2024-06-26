package com.picpaychallenge.application.usecase;

import com.picpaychallenge.application.ApplicationExceptionCodes;
import com.picpaychallenge.application.dto.CreateTransferDTO;
import com.picpaychallenge.application.dto.TransferDTO;
import com.picpaychallenge.application.dto.UserDTO;
import com.picpaychallenge.application.repository.IAuthorizeTransferService;
import com.picpaychallenge.application.repository.ICreateTransferRepository;
import com.picpaychallenge.application.repository.INotifyUserService;
import com.picpaychallenge.common.ExceptionWithCode;
import com.picpaychallenge.domain.entity.TransferEntity;
import com.picpaychallenge.domain.entity.UserEntity;

public class CreateTransferUseCase {
  private final ICreateTransferRepository createTransferRepository;
  private final IAuthorizeTransferService authorizeTransferService;
  private final INotifyUserService notifyUserService;

  public CreateTransferUseCase(
      ICreateTransferRepository createTransferRepository,
      IAuthorizeTransferService authorizeTransferService,
      INotifyUserService notifyUserService) {
    this.createTransferRepository = createTransferRepository;
    this.authorizeTransferService = authorizeTransferService;
    this.notifyUserService = notifyUserService;
  }

  public TransferDTO createTransfer(CreateTransferDTO request) {
    UserEntity payer = this.userDTOToEntity(request.payerDTO());
    UserEntity payee = this.userDTOToEntity(request.payeeDTO());

    TransferEntity transfer = new TransferEntity(payer, payee, request.cents(),
        request.currency());

    if (!this.authorizeTransferService.isAuthorized()) {
      throw new ExceptionWithCode("Unauthorized transfer.",
          ApplicationExceptionCodes.UNAUTHORIZED_TRANSFER.name());
    }

    this.createTransferRepository.create(transfer);

    // TODO: fire and forget?
    this.notifyUserService.notifyUser(payee.getEmail());

    return new TransferDTO(transfer.getId(), request.payerDTO(),
        request.payeeDTO(), transfer.getMoney());
  }

  private UserEntity userDTOToEntity(UserDTO userDTO) {
    return new UserEntity(userDTO.id(), userDTO.fullName(), userDTO.email(), userDTO.document(), userDTO.type(),
        userDTO.cents());
  }
}
