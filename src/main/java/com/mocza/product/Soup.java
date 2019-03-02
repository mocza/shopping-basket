package com.mocza.product;

import com.mocza.basket.Currency;
import com.mocza.basket.Unit;
import com.mocza.product.Product;

import java.math.BigDecimal;

public class Soup extends Product {

  public Soup(Unit unit, BigDecimal unitPrice, Currency currency) {
    this.unit = unit;
    this.unitPrice = unitPrice;
    this.currency = currency;
  }



}
