package com.picpaychallenge.infrastructure.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.picpaychallenge.application.dto.CreateUserDTO;
import com.picpaychallenge.application.dto.UserDTO;
import com.picpaychallenge.application.usecase.CreateUserUseCase;
import com.picpaychallenge.common.ExceptionWithCode;
import com.picpaychallenge.infrastructure.dto.CreateUserControllerDTO;
import com.picpaychallenge.infrastructure.mapper.UserMapper;

@RestController
@RequestMapping("users")
public class UserController {
  private final CreateUserUseCase createUserUseCase;

  public UserController(CreateUserUseCase createUserUseCase) {
    this.createUserUseCase = createUserUseCase;
  }

  @PostMapping
  CreateUserControllerDTO.Response create(@RequestBody CreateUserControllerDTO.Request request)
      throws ExceptionWithCode {
    CreateUserDTO userEntity = UserMapper.controllerRequestToDomainRequest(request);
    UserDTO createdUser = this.createUserUseCase.createUser(userEntity);
    return UserMapper.domainDtoToControllerResponse(createdUser);
  }
}
