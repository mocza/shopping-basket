package com.mocza.product;

import com.mocza.basket.Basket;
import com.mocza.basket.Currency;
import com.mocza.basket.Unit;
import com.mocza.offer.EffectiveOffer;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;


public abstract class Product {
  protected Basket basket;
  protected Optional<EffectiveOffer> effectiveOffer = Optional.empty();
  protected Unit unit;
  protected BigDecimal unitPrice;
  protected Currency currency;
  protected BigDecimal savings = BigDecimal.ZERO;

  public Product(Unit unit, BigDecimal unitPrice, Currency currency) {
    this.unit = unit;
    this.unitPrice = unitPrice;
    this.currency = currency;
  }

  public abstract String getProductNameSingular();

  public abstract String getProductNamePlural();

  public BigDecimal getUnitPrice() {
    return unitPrice;
  }

  public BigDecimal getDiscountedPrice() {
    return effectiveOffer.isPresent() ? effectiveOffer.get().getDiscountedPrice() : unitPrice;
  }

  public void setEffectiveOffer(Optional<EffectiveOffer> effectiveOffer) {
    this.effectiveOffer = effectiveOffer;
  }

  public Optional<EffectiveOffer> getEffectiveOffer() {
    return effectiveOffer;
  }

  public Currency getCurrency() { return currency; }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Product product = (Product) o;
    return unit == product.unit &&
            Objects.equals(unitPrice, product.unitPrice) &&
            currency == product.currency;
  }

  @Override
  public int hashCode() {
    return Objects.hash(unit, unitPrice, currency);
  }
}
