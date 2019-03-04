package com.mocza.product;

import com.mocza.basket.Currency;
import com.mocza.basket.Unit;

import java.math.BigDecimal;
import java.text.MessageFormat;

public class Milk extends Product {

  public Milk(BigDecimal unitPrice) {
    super(Unit.BOTTLE, unitPrice, Currency.GBP);
  }

  public Milk(Unit unit, BigDecimal unitPrice, Currency currency) {
    super(unit, unitPrice, currency);
  }

  @Override
  public String getProductNameSingular() {
    return "milk";
  }

  @Override
  public String getProductNamePlural() {
    return MessageFormat.format("{0} of {1}", unit.getDisplayName(), getProductNameSingular());
  }
}
