package com.mocza.basket;

import com.mocza.offer.Offer;
import com.mocza.product.Product;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;

public class Basket {
  private static final Currency DEFAULT_CURRENCY = Currency.GBP;
  public static final int PRICE_DIGITS = 2;
  private Collection<Product> products;

  public Basket(Collection<Product> products) {
    this.products = products;
    if (getCurrencyCount(products) > 1)
      throw new IllegalStateException("Invalid currency in basket, all products must have same currency.");
  }

  public BigDecimal calculateSubtotal() {
    BigDecimal subtotal = products.stream().map(p -> p.getUnitPrice()).reduce(BigDecimal.ZERO, BigDecimal::add).setScale(PRICE_DIGITS);
    return subtotal;
  }

  public BigDecimal calculateTotal() {
    products.forEach(product -> product.calculateEffectiveOffer(products));
    return products.stream().map(p -> p.getDiscountedPrice()).reduce(BigDecimal.ZERO, BigDecimal::add).setScale(PRICE_DIGITS);
  }

  private long getCurrencyCount(Collection<Product> products) {
    return products.stream().map(p -> p.getCurrency()).distinct().count();
  }

  public Collection<Product> getProducts() {
    return products;
  }

  public Currency getCurrency() {
    Optional<Currency> optionalCurrency = products.stream().map(p -> p.getCurrency()).findFirst();
    return  optionalCurrency.isPresent()? optionalCurrency.get() : DEFAULT_CURRENCY;
  }

}
