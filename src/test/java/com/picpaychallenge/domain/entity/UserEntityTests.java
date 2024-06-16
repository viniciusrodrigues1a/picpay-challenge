package com.picpaychallenge.domain.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.picpaychallenge.common.ExceptionWithCode;
import com.picpaychallenge.domain.DomainExceptionCodes;
import com.picpaychallenge.domain.valueobjects.UserType;

class UserEntityTests {
  @Test
  void shouldThrowInvalidEmailException_givenEmailMissingDomain() {
    try {
      new UserEntity("Vinicius Rodrigues", "viniciusrodrigues.aro", "000.000.000-00",
          UserType.COMMON);

      fail();
    } catch (ExceptionWithCode exception) {
      assertEquals(exception.getCode(), DomainExceptionCodes.INVALID_EMAIL.name());
    }
  }

  @Test
  void shouldThrowInvalidEmailException_givenEmailMissingLocalPart() {
    try {
      new UserEntity("Vinicius Rodrigues", "@gmail.com", "000.000.000-00",
          UserType.COMMON);

      fail();
    } catch (ExceptionWithCode exception) {
      assertEquals(exception.getCode(), DomainExceptionCodes.INVALID_EMAIL.name());
    }
  }

  @Test
  void shouldThrowInvalidEmailException_givenEmailMissingAtSign() {
    try {
      new UserEntity("Vinicius Rodrigues", "viniciusrodrigues.arogmail.com", "000.000.000-00",
          UserType.COMMON);

      fail();
    } catch (ExceptionWithCode exception) {
      assertEquals(exception.getCode(), DomainExceptionCodes.INVALID_EMAIL.name());
    }
  }

  @Test
  void shouldInstantiateUserEntity_givenValidEmail() {
    try {
      String givenEmail = "viniciusrodrigues.aro@gmail.com";
      UserEntity userEntity = new UserEntity("Vinicius Rodrigues", givenEmail, "000.000.000-00",
          UserType.COMMON);

      assertEquals(userEntity.getEmail(), givenEmail);
    } catch (Exception exception) {
      fail();
    }

  }
}
