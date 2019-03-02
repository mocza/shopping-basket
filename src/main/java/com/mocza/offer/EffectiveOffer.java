package com.mocza.offer;

import com.mocza.product.Product;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;

public class EffectiveOffer {
  private Collection<Product> productOfferIsBasedOn;
  private Product productOfferIsAppliesTo;
  private BigDecimal discountRate;
  private BigDecimal savings;

  public EffectiveOffer(Collection<Product> productOfferIsBasedOn, Product productOfferIsAppliesTo, BigDecimal discountRate, BigDecimal savings) {
    this.productOfferIsBasedOn = productOfferIsBasedOn;
    this.productOfferIsAppliesTo = productOfferIsAppliesTo;
    this.discountRate = discountRate;
    this.savings = savings;
  }

  public BigDecimal getDiscountedPrice() {
    return productOfferIsAppliesTo.getUnitPrice().multiply(discountRate).setScale(2);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    EffectiveOffer that = (EffectiveOffer) o;
    return Objects.equals(productOfferIsBasedOn, that.productOfferIsBasedOn) &&
            Objects.equals(productOfferIsAppliesTo, that.productOfferIsAppliesTo) &&
            Objects.equals(savings, that.savings);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productOfferIsBasedOn, productOfferIsAppliesTo, savings);
  }

  @Override
  public String toString() {
    return "com.mocza.offer.EffectiveOffer{" +
            "productOfferIsBasedOn=" + productOfferIsBasedOn +
            ", productOfferIsAppliesTo=" + productOfferIsAppliesTo +
            ", savings=" + savings +
            '}';
  }
}
