package com.picpaychallenge.infrastructure.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity(name = "transfers")
@Table(name = "transfers")
public class TransferModel {
  @Id
  @Column(nullable = false)
  private UUID id;

  @Column(nullable = false)
  private int cents;

  @Column(nullable = false)
  private String currency;

  @ManyToOne
  @JoinColumn(name = "payer_id")
  private UserModel payer;

  @ManyToOne
  @JoinColumn(name = "payee_id")
  private UserModel payee;

  public TransferModel() {
  }

  public TransferModel(UUID id, UserModel payer, UserModel payee, int cents, String currency) {
    this.id = id;
    this.cents = cents;
    this.currency = currency;
    this.payer = payer;
    this.payee = payee;
  }

  public UUID getId() {
    return id;
  }

  public int getCents() {
    return cents;
  }

  public String getCurrency() {
    return currency;
  }

  public UserModel getPayer() {
    return payer;
  }

  public UserModel getPayee() {
    return payee;
  }
}
