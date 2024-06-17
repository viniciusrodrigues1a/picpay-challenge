package com.picpaychallenge.application.usecase;

import java.util.Optional;

import com.picpaychallenge.application.ApplicationExceptionCodes;
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

  public UserEntity createUser(UserEntity user) throws ExceptionWithCode {
    Optional<UserEntity> userWithSameEmail = this.findOneUserByEmailRepository.findOneUserByEmail(user.getEmail());
    if (userWithSameEmail.isPresent())
      throw new ExceptionWithCode("E-mail já existe.",
          ApplicationExceptionCodes.EMAIL_TAKEN.name());

    Optional<UserEntity> userWithSameDocument = this.findOneUserByDocumentRepository
        .findOneUserByDocument(user.getDocument());
    if (userWithSameDocument.isPresent())
      throw new ExceptionWithCode("Documento já utilizado.", ApplicationExceptionCodes.DOCUMENT_TAKEN.name());

    return this.createUserRepository.create(user);
  }
}
