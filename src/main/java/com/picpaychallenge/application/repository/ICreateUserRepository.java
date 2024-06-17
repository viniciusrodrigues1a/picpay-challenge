package com.picpaychallenge.application.repository;

import com.picpaychallenge.application.dto.UserDTO;
import com.picpaychallenge.domain.entity.UserEntity;

public interface ICreateUserRepository {
  public UserDTO create(UserEntity user, String password);
}
