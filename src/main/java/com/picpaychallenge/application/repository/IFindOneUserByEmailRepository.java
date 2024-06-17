package com.picpaychallenge.application.repository;

import java.util.Optional;

import com.picpaychallenge.application.dto.UserDTO;

public interface IFindOneUserByEmailRepository {
  public Optional<UserDTO> findOneUserByEmail(String email);
}
