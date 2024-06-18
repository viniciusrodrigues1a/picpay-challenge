package com.picpaychallenge.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.picpaychallenge.application.usecase.CreateUserUseCase;
import com.picpaychallenge.infrastructure.gateway.UserRepositoryGateway;
import com.picpaychallenge.infrastructure.repository.UserRepository;

@Configuration
public class UserConfiguration {
  @Bean
  CreateUserUseCase createUserUseCase(UserRepositoryGateway userRepositoryGateway) {
    return new CreateUserUseCase(userRepositoryGateway, userRepositoryGateway, userRepositoryGateway);
  }

  @Bean
  UserRepositoryGateway userRepositoryGateway(UserRepository userRepository) {
    return new UserRepositoryGateway(userRepository);
  }
}
