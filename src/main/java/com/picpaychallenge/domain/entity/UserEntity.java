package com.picpaychallenge.domain.entity;

import java.util.UUID;
import java.util.Currency;
import java.util.regex.Pattern;

import com.picpaychallenge.common.ExceptionWithCode;
import com.picpaychallenge.domain.DomainExceptionCodes;
import com.picpaychallenge.domain.valueobjects.DocumentType;
import com.picpaychallenge.domain.valueobjects.Money;
import com.picpaychallenge.domain.valueobjects.UserType;

public class UserEntity {
  private UUID id;
  private String fullName;
  private String email;
  private String document;
  private DocumentType documentType;
  private UserType type;
  private Money balance;

  public UserEntity(UUID id, String fullName, String email, String document, UserType type, int cents)
      throws ExceptionWithCode {
    if (!this.isEmailValid(email)) {
      throw new ExceptionWithCode("E-mail inválido.", DomainExceptionCodes.INVALID_EMAIL.name());
    }

    if (id != null) {
      this.id = id;
    } else {
      this.id = UUID.randomUUID();
    }
    this.fullName = fullName;
    this.email = email;
    this.document = document;
    this.documentType = isCNPJ(document) ? DocumentType.CNPJ : DocumentType.CPF;
    this.type = type;
    this.balance = new Money(cents, Currency.getInstance("BRL"));
  }

  public UUID getId() {
    return this.id;
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

  public Money getBalance() {
    return this.balance;
  }

  private boolean isEmailValid(String email) {
    String regexPattern = "^(.+)@(\\S+)$";
    return Pattern.compile(regexPattern).matcher(email).matches();
  }

  private boolean isCNPJ(String document) {
    Pattern cnpjPattern = Pattern.compile("\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}");
    return cnpjPattern.matcher(document).matches();
  }
}
