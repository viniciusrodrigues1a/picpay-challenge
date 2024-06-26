package com.picpaychallenge.infrastructure.mapper;

import java.util.Currency;
import java.util.UUID;

import com.picpaychallenge.application.dto.CreateTransferDTO;
import com.picpaychallenge.application.dto.TransferDTO;
import com.picpaychallenge.application.dto.UserDTO;
import com.picpaychallenge.domain.entity.TransferEntity;
import com.picpaychallenge.domain.valueobjects.Money;
import com.picpaychallenge.infrastructure.dto.CreateTransferControllerDTO;
import com.picpaychallenge.infrastructure.model.TransferModel;
import com.picpaychallenge.infrastructure.model.UserModel;

public class TransferMapper {
  public static TransferDTO modelToBusinessDTO(TransferModel model) {
    UserDTO payerDTO = UserMapper.modelToBusinessDTO(model.getPayer());
    UserDTO payeeDTO = UserMapper.modelToBusinessDTO(model.getPayee());
    Money money = new Money(model.getCents(), Currency.getInstance(model.getCurrency()));
    return new TransferDTO(model.getId(), payerDTO, payeeDTO, money);
  }

  public static TransferModel businessEntityToModel(UUID id, TransferEntity entity) {
    UserModel payerModel = UserMapper.businessEntityToModel(entity.getPayer(), null);
    UserModel payeeModel = UserMapper.businessEntityToModel(entity.getPayee(), null);
    return new TransferModel(id, payerModel, payeeModel, entity.getMoney().getCents(),
        entity.getMoney().getCurrency().getCurrencyCode());
  }

  public static CreateTransferControllerDTO.Response domainDtoToControllerResponse(TransferDTO user) {
    return new CreateTransferControllerDTO.Response(user.id());
  }

  public static CreateTransferDTO controllerRequestToDomainRequest(CreateTransferControllerDTO.Request request,
      UserModel payerModel, UserModel payeeModel) {
    UserDTO payerDTO = UserMapper.modelToBusinessDTO(payerModel);
    UserDTO payeeDTO = UserMapper.modelToBusinessDTO(payeeModel);
    return new CreateTransferDTO(payerDTO, payeeDTO, request.cents(), request.currency());
  }
}
