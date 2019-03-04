package com.mocza.offer;

import com.mocza.product.Product;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;

import static com.mocza.basket.Basket.PRICE_DIGITS;

/**
 * An EffectiveOffer describes what an Offer applied to a Product means when calculating the total.
 */
public class EffectiveOffer {
  private Product productOfferIsAppliesTo;
  private Collection<Product> productOfferIsBasedOn;
  private BigDecimal discountRate;
  private BigDecimal savings;

  public EffectiveOffer(Product productOfferAppliesTo, Collection<Product> productOfferIsBasedOn, BigDecimal discountRate, BigDecimal savings) {
    this.productOfferIsBasedOn = productOfferIsBasedOn;
    this.productOfferIsAppliesTo = productOfferAppliesTo;
    this.discountRate = discountRate;
    this.savings = savings;
  }

  public BigDecimal getDiscountedPrice() {
    return productOfferIsAppliesTo.getUnitPrice().multiply(discountRate).setScale(PRICE_DIGITS);
  }

  public Collection<Product> getProductOfferIsBasedOn() {
    return productOfferIsBasedOn;
  }

  public Product getProductOfferIsAppliesTo() {
    return productOfferIsAppliesTo;
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
