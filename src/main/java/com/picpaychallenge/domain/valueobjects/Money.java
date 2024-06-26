package com.picpaychallenge.domain.valueobjects;

import java.util.Currency;
import java.util.Objects;

import com.picpaychallenge.common.ExceptionWithCode;
import com.picpaychallenge.domain.DomainExceptionCodes;

public class Money {
  private final int cents;
  private final Currency currency;

  public Money(int cents, Currency currency) {
    this.cents = cents;
    this.currency = currency;
  }

  public int getCents() {
    return cents;
  }

  public Currency getCurrency() {
    return currency;
  }

  public Money add(Money other) {
    checkCurrency(other);
    return new Money(this.cents + other.cents, this.currency);
  }

  public Money subtract(Money other) {
    checkCurrency(other);
    return new Money(this.cents - other.cents, this.currency);
  }

  public Money multiply(int multiplier) {
    return new Money(this.cents * multiplier, this.currency);
  }

  public Money divide(int divisor) {
    return new Money(this.cents / divisor, this.currency);
  }

  public boolean isGreaterThan(Money other) {
    checkCurrency(other);
    return this.cents > other.cents;
  }

  public boolean isLessThan(Money other) {
    checkCurrency(other);
    return this.cents < other.cents;
  }

  private void checkCurrency(Money other) {
    if (!this.currency.equals(other.currency)) {
      throw new ExceptionWithCode("Currencies must match", DomainExceptionCodes.CURRENCIES_MUST_MATCH.name());
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Money money = (Money) o;
    return cents == money.cents && currency.equals(money.currency);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cents, currency);
  }

  @Override
  public String toString() {
    return currency.getSymbol() + " " + (cents / 100.0);
  }
}
