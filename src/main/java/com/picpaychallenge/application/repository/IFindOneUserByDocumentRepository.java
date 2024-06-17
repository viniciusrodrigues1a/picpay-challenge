package com.picpaychallenge.application.repository;

import java.util.Optional;

import com.picpaychallenge.application.dto.UserDTO;

public interface IFindOneUserByDocumentRepository {
  public Optional<UserDTO> findOneUserByDocument(String document);
}
