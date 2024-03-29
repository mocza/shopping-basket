package com.mocza.product;

import com.mocza.basket.Currency;
import com.mocza.basket.Unit;

import java.math.BigDecimal;

public class Apple extends Product {

  public Apple(BigDecimal unitPrice) {
    super(Unit.BAG, unitPrice, Currency.GBP);
  }

  @Override
  public String getProductNameSingular() {
    return "apple";
  }

  @Override
  public String getProductNamePlural() {
    return "apples";
  }
}
