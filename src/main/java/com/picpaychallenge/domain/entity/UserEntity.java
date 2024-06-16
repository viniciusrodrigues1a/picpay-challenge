package com.picpaychallenge.domain.entity;

import java.util.regex.Pattern;

import com.picpaychallenge.common.ExceptionWithCode;
import com.picpaychallenge.domain.DomainExceptionCodes;
import com.picpaychallenge.domain.valueobjects.DocumentType;
import com.picpaychallenge.domain.valueobjects.UserType;

public class UserEntity {
  private String fullName;
  private String email;
  private String document;
  private DocumentType documentType;
  private UserType type;

  public UserEntity(String fullName, String email, String document, UserType type) throws ExceptionWithCode {
    if (!this.isEmailValid(email)) {
      throw new ExceptionWithCode("E-mail inválido.", DomainExceptionCodes.INVALID_EMAIL.name());
    }

    this.fullName = fullName;
    this.email = email;
    this.document = document;
    this.documentType = DocumentType.CPF;
    this.type = type;
  }

  public String getFullName() {
    return this.fullName;
  }

  public String getEmail() {
    return this.email;
  }

  public String getDocument() {
    return this.document;
  }

  public DocumentType getDocumentType() {
    return this.documentType;
  }

  public UserType getUserType() {
    return this.type;
  }

  private boolean isEmailValid(String email) {
    String regexPattern = "^(.+)@(\\S+)$";
    return Pattern.compile(regexPattern).matcher(email).matches();
  }
}
