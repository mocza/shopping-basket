package com.mocza.offer;

import com.mocza.product.Product;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

public abstract class Offer<T extends Product> {
  protected BigDecimal discountRate;

  public Offer(BigDecimal discountRate) {
    this.discountRate = discountRate;
  }

  abstract boolean isEligible(T product, Collection<Product> products);

  abstract Collection<Product> getProductsOfferIsBasedOn(T product, Collection<Product> products);

  public BigDecimal getDiscountedPrice(T product, Collection<Product> products) {
    if (isEligible(product, products))
      return product.getUnitPrice().multiply(discountRate);
    else
      return product.getUnitPrice();
  }

  public Optional<EffectiveOffer> getEffectiveOffer(T product, Collection<Product> products) {
    if (isEligible(product, products))
      return Optional.of(new EffectiveOffer(product, getProductsOfferIsBasedOn(product, products),
        discountRate, getSavings(product, products)));
    else
      return Optional.empty();
  }

  protected BigDecimal getSavings(T product, Collection<Product> products) {
    return product.getUnitPrice().subtract(getDiscountedPrice(product, products));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Offer offer = (Offer) o;
    return Objects.equals(discountRate, offer.discountRate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(discountRate);
  }

  @Override
  public String toString() {
    return "com.mocza.offer.Offer{" +
            "discountRate=" + discountRate +
            '}';
  }
}
