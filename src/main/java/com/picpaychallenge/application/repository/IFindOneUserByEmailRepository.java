package com.picpaychallenge.application.repository;

import java.util.Optional;

import com.picpaychallenge.domain.entity.UserEntity;

public interface IFindOneUserByEmailRepository {
  public Optional<UserEntity> findOneUserByEmail(String email);
}
