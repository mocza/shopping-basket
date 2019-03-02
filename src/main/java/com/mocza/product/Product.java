package com.mocza.product;

import com.mocza.basket.Currency;
import com.mocza.basket.PriceBasket;
import com.mocza.basket.Unit;
import com.mocza.offer.EffectiveOffer;

import java.math.BigDecimal;
import java.util.Optional;


public abstract class Product {
  protected PriceBasket priceBasket;
  protected Optional<EffectiveOffer> effectiveOffer;
  protected Unit unit;
  protected BigDecimal unitPrice;
  protected Currency currency;
  protected BigDecimal savings = BigDecimal.ZERO;

  public BigDecimal getUnitPrice() {
    return unitPrice;
  }

  public BigDecimal getDiscountedPrice() {
    return effectiveOffer.isPresent() ? effectiveOffer.get().getDiscountedPrice() : unitPrice;
  }

  public Product addSaving(BigDecimal saving) {
    savings = savings.add(saving).setScale(2);
    return this;
  }

  public void setEffectiveOffer(Optional<EffectiveOffer> effectiveOffer) {
    this.effectiveOffer = effectiveOffer;
  }

  public Optional<EffectiveOffer> getEffectiveOffer() {
    return effectiveOffer;
  }
}
