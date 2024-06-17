package com.picpaychallenge.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.picpaychallenge.application.ApplicationExceptionCodes;
import com.picpaychallenge.application.repository.ICreateUserRepository;
import com.picpaychallenge.application.repository.IFindOneUserByDocumentRepository;
import com.picpaychallenge.application.repository.IFindOneUserByEmailRepository;
import com.picpaychallenge.common.ExceptionWithCode;
import com.picpaychallenge.domain.entity.UserEntity;
import com.picpaychallenge.domain.valueobjects.UserType;

@ExtendWith(MockitoExtension.class)
class CreateUserUseCaseTests {
  @Mock
  private ICreateUserRepository createUserRepository;

  @Mock
  private IFindOneUserByEmailRepository findOneUserByEmailRepository;

  @Mock
  private IFindOneUserByDocumentRepository findOneUserByDocumentRepository;

  private CreateUserUseCase createUserUseCase;

  @BeforeEach
  void setUp() {
    this.createUserUseCase = new CreateUserUseCase(this.createUserRepository,
        this.findOneUserByEmailRepository, this.findOneUserByDocumentRepository);
  }

  @Test
  void shouldThrowEmailTakenException() {
    try {
      // Given
      UserEntity givenUser = new UserEntity("Vinicius", "viniciusrodrigues.aro@gmail.com",
          "111.111.111-11",
          UserType.COMMON);
      UserEntity mockedExistingUser = new UserEntity("Vinicius Rodrigues", "viniciusrodrigues.aro@gmail.com",
          "000.000.000-00",
          UserType.COMMON);
      Mockito.when(this.findOneUserByEmailRepository.findOneUserByEmail(mockedExistingUser.getEmail()))
          .thenReturn(Optional.of(mockedExistingUser));

      // When
      this.createUserUseCase.createUser(givenUser);

      fail();
    } catch (ExceptionWithCode exception) {
      // Then
      assertEquals(exception.getCode(), ApplicationExceptionCodes.EMAIL_TAKEN.name());
    }
  }

  void shouldThrowDocumentTakenException() {
    try {
      // Given
      UserEntity givenUser = new UserEntity("Vinicius",
          "vinicius.rodrigues@gmail.com",
          "000.000.000-00",
          UserType.COMMON);
      UserEntity mockedExistingUser = new UserEntity("Vinicius Rodrigues",
          "viniciusrodrigues.aro@gmail.com",
          "000.000.000-00",
          UserType.COMMON);
      Mockito.lenient()
          .when(this.findOneUserByDocumentRepository.findOneUserByDocument(mockedExistingUser.getDocument()))
          .thenReturn(Optional.of(mockedExistingUser));

      // When
      this.createUserUseCase.createUser(givenUser);

      fail();
    } catch (ExceptionWithCode exception) {
      // Then
      assertEquals(exception.getCode(),
          ApplicationExceptionCodes.DOCUMENT_TAKEN.name());
    }
  }

  @Test
  void shouldCallCreateUserRepository() {
    try {

      // Given
      UserEntity givenUser = new UserEntity("Vinicius",
          "vinicius.rodrigues@gmail.com",
          "000.000.000-00",
          UserType.COMMON);

      // When
      createUserUseCase.createUser(givenUser);

      // Then
      Mockito.verify(this.createUserRepository).create(givenUser);
    } catch (ExceptionWithCode exception) {
      fail();
    }
  }
}
