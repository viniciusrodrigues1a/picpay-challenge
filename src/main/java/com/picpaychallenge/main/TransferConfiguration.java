package com.picpaychallenge.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.picpaychallenge.application.repository.IAuthorizeTransferService;
import com.picpaychallenge.application.repository.INotifyUserService;
import com.picpaychallenge.application.usecase.CreateTransferUseCase;
import com.picpaychallenge.infrastructure.gateway.TransferRepositoryGateway;
import com.picpaychallenge.infrastructure.repository.TransferRepository;
import com.picpaychallenge.infrastructure.repository.UserRepository;
import com.picpaychallenge.infrastructure.service.AuthorizeTransferService;
import com.picpaychallenge.infrastructure.service.NotifyUserService;

@Configuration
public class TransferConfiguration {
  @Bean
  CreateTransferUseCase createTransferUseCase(TransferRepositoryGateway transferRepositoryGateway,
      IAuthorizeTransferService authorizeTransferService,
      INotifyUserService notifyUserService) {
    return new CreateTransferUseCase(transferRepositoryGateway, authorizeTransferService, notifyUserService);
  }

  @Bean
  TransferRepositoryGateway transferRepositoryGateway(TransferRepository transferRepository,
      UserRepository userRepository) {
    return new TransferRepositoryGateway(transferRepository, userRepository);
  }

  @Bean
  IAuthorizeTransferService authorizeTransferService() {
    return new AuthorizeTransferService();
  }

  @Bean
  INotifyUserService notifyUserService() {
    return new NotifyUserService();
  }
}
