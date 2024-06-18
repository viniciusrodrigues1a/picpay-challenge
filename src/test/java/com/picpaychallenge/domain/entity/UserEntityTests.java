package com.picpaychallenge.domain.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.picpaychallenge.common.ExceptionWithCode;
import com.picpaychallenge.domain.DomainExceptionCodes;
import com.picpaychallenge.domain.valueobjects.UserType;
import com.picpaychallenge.domain.valueobjects.DocumentType;

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

  @Test
  void shouldSetDocumentTypeToCNPJ_givenTheDocumentIsCNPJ() {
    try {
      String givenDocument = "43.707.684/0001-79";
      UserEntity userEntity = new UserEntity("Vinicius Rodrigues", "viniciusrodrigues.aro@gmail.com", givenDocument,
          UserType.COMMON);

      assertEquals(DocumentType.CNPJ, userEntity.getDocumentType());
    } catch (Exception exception) {
      fail();
    }
  }

  @Test
  void shouldSetDocumentTypeToCPF_givenTheDocumentIsCPF() {
    try {
      String givenDocument = "792.189.250-75";
      UserEntity userEntity = new UserEntity("Vinicius Rodrigues", "viniciusrodrigues.aro@gmail.com", givenDocument,
          UserType.COMMON);

      assertEquals(DocumentType.CPF, userEntity.getDocumentType());
    } catch (Exception exception) {
      fail();
    }
  }

  @Test
  void shouldSetDocumentTypeToCPF_givenTheDocumentIsInvalid() {
    try {
      String givenDocument = "792189";
      UserEntity userEntity = new UserEntity("Vinicius Rodrigues", "viniciusrodrigues.aro@gmail.com", givenDocument,
          UserType.COMMON);

      assertEquals(DocumentType.CPF, userEntity.getDocumentType());
    } catch (Exception exception) {
      fail();
    }
  }
}
