package com.mocza.offer;

import com.mocza.product.Product;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

public abstract class Offer {
  protected BigDecimal discountRate;

  public Offer(BigDecimal discountRate) {
    this.discountRate = discountRate;
  }

  abstract Collection<Product> getProductsOfferIsBasedOn(Product product, Collection<Product> products);

  abstract boolean isEligible(Product product, Collection<Product> products);

  public BigDecimal getDiscountedPrice(Product product, Collection<Product> products) {
    if (isEligible(product, products))
      return product.getUnitPrice().multiply(discountRate).setScale(2);
    else
      return product.getUnitPrice();
  }

  public Optional<EffectiveOffer> getEffectiveOffer(Product product, Collection<Product> products){
    if (isEligible(product, products))
      return Optional.of(new EffectiveOffer(product, getProductsOfferIsBasedOn(product, products),
              discountRate, getSavings(product, products)));
    else
      return Optional.empty();
  }

  private BigDecimal getSavings(Product product, Collection<Product> products) {
    return product.getUnitPrice().subtract(getDiscountedPrice(product, products)).setScale(2);
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
