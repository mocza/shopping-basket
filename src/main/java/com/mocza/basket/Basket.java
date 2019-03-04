package com.mocza.basket;

import com.mocza.offer.EffectiveOffer;
import com.mocza.offer.Offer;
import com.mocza.product.Product;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;

public class Basket {
  private static final Currency DEFAULT_CURRENCY = Currency.GBP;
  private Collection<Product> products;

  public Basket(Collection<Product> products) {
    this.products = products;
    if (getCurrencyCount(products) > 1)
      throw new IllegalStateException("Invalid currency in basket, all products must have same currency.");
  }

  public BigDecimal calculateSubtotal() {
    BigDecimal subtotal = products.stream().map(p -> p.getUnitPrice()).reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2);
    return subtotal;
  }

  public BigDecimal calculateTotal(Collection<Offer> offers) {
    products.forEach(p -> p.setEffectiveOffer(getEffectiveOffer(p, offers)));
    return products.stream().map(p -> p.getDiscountedPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  private long getCurrencyCount(Collection<Product> products) {
    return products.stream().map(p -> p.getCurrency()).distinct().count();
  }

  private Optional<EffectiveOffer> getEffectiveOffer(Product product, Collection<Offer> offers) {
    return offers.stream().map(offer -> offer.getEffectiveOffer(product, products))
            .filter(optional -> optional.isPresent()).map(optional -> ((Optional<EffectiveOffer>)optional).get()).findFirst();
  }

  public Collection<Product> getProducts() {
    return products;
  }

  public Currency getCurrency() {
    Optional<Currency> optionalCurrency = products.stream().map(p -> p.getCurrency()).findFirst();
    return  optionalCurrency.isPresent()? optionalCurrency.get() : DEFAULT_CURRENCY;
  }

}
