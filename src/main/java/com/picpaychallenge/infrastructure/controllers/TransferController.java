package com.picpaychallenge.infrastructure.controllers;

import java.util.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.picpaychallenge.application.dto.CreateTransferDTO;
import com.picpaychallenge.application.dto.TransferDTO;
import com.picpaychallenge.application.usecase.CreateTransferUseCase;
import com.picpaychallenge.infrastructure.dto.CreateTransferControllerDTO;
import com.picpaychallenge.infrastructure.mapper.TransferMapper;
import com.picpaychallenge.infrastructure.model.UserModel;
import com.picpaychallenge.infrastructure.repository.UserRepository;

@RestController
@RequestMapping("transfers")
public class TransferController {
  private final CreateTransferUseCase createTransferUseCase;
  private final UserRepository userRepository;

  public TransferController(CreateTransferUseCase createTransferUseCase,
      UserRepository userRepository) {
    this.createTransferUseCase = createTransferUseCase;
    this.userRepository = userRepository;
  }

  @PostMapping
  CreateTransferControllerDTO.Response create(@RequestBody CreateTransferControllerDTO.Request request)
      throws Exception {
    Optional<UserModel> payerModel = this.userRepository.findById(request.payerId());
    Optional<UserModel> payeeModel = this.userRepository.findById(request.payeeId());

    if (payerModel.isEmpty()) {
      throw new Exception("Payer not found.");
    }

    if (payeeModel.isEmpty()) {
      throw new Exception("Payee not found.");
    }

    CreateTransferDTO createTransferDTO = TransferMapper.controllerRequestToDomainRequest(request, payerModel.get(),
        payeeModel.get());

    TransferDTO createdTransfer = this.createTransferUseCase.createTransfer(createTransferDTO);

    return TransferMapper.domainDtoToControllerResponse(createdTransfer);
  }
}
