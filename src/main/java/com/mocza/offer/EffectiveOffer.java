package com.mocza.offer;

import com.mocza.product.Product;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;

/**
 * EffectiveOffer describes the impact of an Offer that is applied to a concrete product.
 */
public class EffectiveOffer {
  private Product offerAppliesTo;
  private Collection<Product> offerIsBasedOn;
  private BigDecimal discountRate;
  private BigDecimal savings;

  public EffectiveOffer(Product offerAppliesTo, Collection<Product> offerIsBasedOn, BigDecimal discountRate, BigDecimal savings) {
    this.offerIsBasedOn = offerIsBasedOn;
    this.offerAppliesTo = offerAppliesTo;
    this.discountRate = discountRate;
    this.savings = savings;
  }

  public BigDecimal getDiscountedPrice() {
    return offerAppliesTo.getUnitPrice().multiply(discountRate);
  }

  public Collection<Product> getOfferIsBasedOn() {
    return offerIsBasedOn;
  }

  public Product getOfferAppliesTo() {
    return offerAppliesTo;
  }

  public BigDecimal getDiscountRate() {
    return discountRate;
  }

  public BigDecimal getSavings() {
    return savings;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    EffectiveOffer that = (EffectiveOffer) o;
    return Objects.equals(offerIsBasedOn, that.offerIsBasedOn) &&
            Objects.equals(offerAppliesTo, that.offerAppliesTo) &&
            Objects.equals(savings, that.savings);
  }

  @Override
  public int hashCode() {
    return Objects.hash(offerIsBasedOn, offerAppliesTo, savings);
  }

  @Override
  public String toString() {
    return "com.mocza.offer.EffectiveOffer{" +
            "offerIsBasedOn=" + offerIsBasedOn +
            ", offerAppliesTo=" + offerAppliesTo +
            ", savings=" + savings +
            '}';
  }
}
