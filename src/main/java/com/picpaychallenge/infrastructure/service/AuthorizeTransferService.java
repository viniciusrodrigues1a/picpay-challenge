package com.picpaychallenge.infrastructure.service;

import com.picpaychallenge.application.repository.IAuthorizeTransferService;

public class AuthorizeTransferService implements IAuthorizeTransferService {
  @Override
  public boolean isAuthorized() {
    return true;
  }

}
