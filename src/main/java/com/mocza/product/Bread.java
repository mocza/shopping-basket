package com.mocza.product;

import com.mocza.basket.Currency;
import com.mocza.basket.Unit;

import java.math.BigDecimal;
import java.text.MessageFormat;

public class Bread extends Product {

  public Bread(BigDecimal unitPrice) {
    super(Unit.LOAF, unitPrice, Currency.GBP);
  }

  public Bread(Unit unit, BigDecimal unitPrice, Currency currency) {
    super(unit, unitPrice, currency);
  }

  @Override
  public String getProductNameSingular() {
    return "bread";
  }

  @Override
  public String getProductNamePlural() {
    return MessageFormat.format("{0}s of {1}", unit.getDisplayName(), getProductNameSingular());
  }
}
