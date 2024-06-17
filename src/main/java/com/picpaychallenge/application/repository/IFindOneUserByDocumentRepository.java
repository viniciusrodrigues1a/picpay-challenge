package com.picpaychallenge.application.repository;

import java.util.Optional;

import com.picpaychallenge.domain.entity.UserEntity;

public interface IFindOneUserByDocumentRepository {
  public Optional<UserEntity> findOneUserByDocument(String document);
}
