package com.mocza.product;

import com.mocza.basket.Currency;
import com.mocza.basket.Unit;

import java.math.BigDecimal;

public class Soup extends Product {

  public Soup(BigDecimal unitPrice) {
    super(Unit.TIN, unitPrice, Currency.GBP);
  }

  public Soup(Unit unit, BigDecimal unitPrice, Currency currency) {
    super(unit, unitPrice, currency);
  }

  @Override
  public String getProductNameSingular() {
    return "soup";
  }

  @Override
  public String getProductNamePlural() {
    return "soups";
  }
}
