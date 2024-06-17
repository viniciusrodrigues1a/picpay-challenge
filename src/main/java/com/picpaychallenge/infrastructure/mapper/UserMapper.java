package com.picpaychallenge.infrastructure.mapper;

import com.picpaychallenge.application.dto.CreateUserDTO;
import com.picpaychallenge.application.dto.UserDTO;
import com.picpaychallenge.domain.entity.UserEntity;
import com.picpaychallenge.infrastructure.dto.CreateUserControllerDTO;
import com.picpaychallenge.infrastructure.model.UserModel;

public class UserMapper {
  public static UserDTO modelToBusinessDTO(UserModel model) {
    return new UserDTO(model.getFullName(), model.getEmail(), model.getDocument(), model.getDocumentType(),
        model.getUserType());
  }

  public static UserModel businessDtoToModel(UserDTO businessDto, String password) {
    return new UserModel(businessDto.email(), businessDto.fullName(), businessDto.document(),
        businessDto.documentType(),
        businessDto.type(), password);
  }

  public static UserModel businessEntityToModel(UserEntity entity, String password) {
    return new UserModel(entity.getEmail(), entity.getFullName(), entity.getDocument(),
        entity.getDocumentType(),
        entity.getUserType(), password);
  }

  public static CreateUserControllerDTO.Response domainDtoToControllerResponse(UserDTO user) {
    return new CreateUserControllerDTO.Response(user.fullName(), user.email(), user.type());
  }

  public static CreateUserDTO controllerRequestToDomainRequest(CreateUserControllerDTO.Request request) {
    return new CreateUserDTO(request.fullName(), request.email(), request.password(), request.document(),
        request.type());
  }
}
