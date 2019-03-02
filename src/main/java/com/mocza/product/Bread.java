package com.mocza.product;

import com.mocza.basket.Currency;
import com.mocza.basket.Unit;
import com.mocza.product.Product;

import java.math.BigDecimal;

public class Bread extends Product {

  public Bread(Unit unit, BigDecimal unitPrice, Currency currency) {
    this.unit = unit;
    this.unitPrice = unitPrice;
    this.currency = currency;
  }

}
