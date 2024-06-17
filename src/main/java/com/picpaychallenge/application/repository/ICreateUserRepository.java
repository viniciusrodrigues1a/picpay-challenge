package com.picpaychallenge.application.repository;

import com.picpaychallenge.domain.entity.UserEntity;

public interface ICreateUserRepository {
  public UserEntity create(UserEntity user);
}
