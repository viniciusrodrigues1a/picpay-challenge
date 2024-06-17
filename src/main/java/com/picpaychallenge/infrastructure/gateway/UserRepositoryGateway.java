package com.picpaychallenge.infrastructure.gateway;

import java.util.Optional;

import com.picpaychallenge.application.dto.UserDTO;
import com.picpaychallenge.application.repository.ICreateUserRepository;
import com.picpaychallenge.application.repository.IFindOneUserByDocumentRepository;
import com.picpaychallenge.application.repository.IFindOneUserByEmailRepository;
import com.picpaychallenge.domain.entity.UserEntity;
import com.picpaychallenge.infrastructure.repository.UserRepository;
import com.picpaychallenge.infrastructure.mapper.UserMapper;
import com.picpaychallenge.infrastructure.model.UserModel;

public class UserRepositoryGateway
    implements ICreateUserRepository, IFindOneUserByEmailRepository, IFindOneUserByDocumentRepository {
  private final UserRepository userRepository;

  public UserRepositoryGateway(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDTO create(UserEntity user, String password) {
    UserModel model = UserMapper.businessEntityToModel(user, password);
    UserModel savedModel = this.userRepository.save(model);
    UserDTO dto = UserMapper.modelToBusinessDTO(savedModel);
    return dto;
  }

  @Override
  public Optional<UserDTO> findOneUserByEmail(String email) {
    Optional<UserModel> user = this.userRepository.findUserByEmail(email);

    if (user.isPresent())
      return Optional.of(UserMapper.modelToBusinessDTO(user.get()));

    return Optional.empty();
  }

  @Override
  public Optional<UserDTO> findOneUserByDocument(String document) {
    Optional<UserModel> user = this.userRepository.findUserByDocument(document);

    if (user.isPresent())
      return Optional.of(UserMapper.modelToBusinessDTO(user.get()));

    return Optional.empty();
  }
}
