package com.picpaychallenge.domain.entity;

import java.util.Currency;
import java.util.UUID;

import com.picpaychallenge.common.ExceptionWithCode;
import com.picpaychallenge.domain.DomainExceptionCodes;
import com.picpaychallenge.domain.valueobjects.Money;
import com.picpaychallenge.domain.valueobjects.UserType;

public class TransferEntity {
  private UUID id;
  private UserEntity payer;
  private UserEntity payee;
  private Money money;

  public TransferEntity(UserEntity payer, UserEntity payee, int cents, String currency) {
    if (payer.getUserType() == UserType.MERCHANT) {
      throw new ExceptionWithCode("Merchant cannot transfer money.",
          DomainExceptionCodes.MERCHANT_CANT_TRANSFER.name());
    }

    Money money = new Money(cents, Currency.getInstance(currency));

    if (payer.getBalance().isLessThan(money)) {
      throw new ExceptionWithCode("Insufficient balance.", DomainExceptionCodes.INSUFFICIENT_BALANCE.name());
    }

    this.id = UUID.randomUUID();
    this.money = money;
    this.payer = payer;
    this.payee = payee;
  }

  public UUID getId() {
    return this.id;
  }

  public UserEntity getPayer() {
    return this.payer;
  }

  public UserEntity getPayee() {
    return this.payee;
  }

  public Money getMoney() {
    return this.money;
  }
}
