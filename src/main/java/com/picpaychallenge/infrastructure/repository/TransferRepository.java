package com.picpaychallenge.infrastructure.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.picpaychallenge.infrastructure.model.TransferModel;

public interface TransferRepository extends CrudRepository<TransferModel, UUID> {
}
