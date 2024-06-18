package com.picpaychallenge.application.usecase;

import java.util.Optional;

import com.picpaychallenge.application.ApplicationExceptionCodes;
import com.picpaychallenge.application.dto.CreateUserDTO;
import com.picpaychallenge.application.dto.UserDTO;
import com.picpaychallenge.application.repository.ICreateUserRepository;
import com.picpaychallenge.application.repository.IFindOneUserByDocumentRepository;
import com.picpaychallenge.application.repository.IFindOneUserByEmailRepository;
import com.picpaychallenge.domain.entity.UserEntity;
import com.picpaychallenge.common.ExceptionWithCode;

public class CreateUserUseCase {
  private final ICreateUserRepository createUserRepository;
  private final IFindOneUserByEmailRepository findOneUserByEmailRepository;
  private final IFindOneUserByDocumentRepository findOneUserByDocumentRepository;

  public CreateUserUseCase(
      ICreateUserRepository createUserRepository,
      IFindOneUserByEmailRepository findOneUserByEmailRepository,
      IFindOneUserByDocumentRepository findOneUserByDocumentRepository) {
    this.createUserRepository = createUserRepository;
    this.findOneUserByEmailRepository = findOneUserByEmailRepository;
    this.findOneUserByDocumentRepository = findOneUserByDocumentRepository;
  }

  public UserDTO createUser(CreateUserDTO request) throws ExceptionWithCode {
    UserEntity user = new UserEntity(request.fullName(), request.email(), request.document(), request.type());

    Optional<UserDTO> userWithSameEmail = this.findOneUserByEmailRepository.findOneUserByEmail(user.getEmail());
    if (userWithSameEmail.isPresent())
      throw new ExceptionWithCode("E-mail já existe.",
          ApplicationExceptionCodes.EMAIL_TAKEN.name());

    Optional<UserDTO> userWithSameDocument = this.findOneUserByDocumentRepository
        .findOneUserByDocument(user.getDocument());
    if (userWithSameDocument.isPresent())
      throw new ExceptionWithCode("Documento já utilizado.", ApplicationExceptionCodes.DOCUMENT_TAKEN.name());

    this.createUserRepository.create(user, request.password());

    return new UserDTO(user.getFullName(), user.getEmail(), user.getDocument(), user.getDocumentType(),
        user.getUserType());
  }
}
