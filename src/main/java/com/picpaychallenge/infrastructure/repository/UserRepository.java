package com.picpaychallenge.infrastructure.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.picpaychallenge.infrastructure.model.UserModel;

public interface UserRepository extends CrudRepository<UserModel, UUID> {
  Optional<UserModel> findUserByEmail(String email);

  Optional<UserModel> findUserByDocument(String document);
}
