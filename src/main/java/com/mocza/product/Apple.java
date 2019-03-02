package com.mocza.product;

import com.mocza.basket.Currency;
import com.mocza.basket.Unit;
import com.mocza.product.Product;

import java.math.BigDecimal;

public class Apple extends Product {

  public Apple(Unit unit, BigDecimal unitPrice, Currency currency) {
    this.unit = unit;
    this.unitPrice = unitPrice;
    this.currency = currency;
  }


}
