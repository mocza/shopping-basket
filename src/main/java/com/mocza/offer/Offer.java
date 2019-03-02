package com.mocza.offer;

import com.mocza.product.Product;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public abstract class Offer<C1 extends Class, C2 extends Class> {
  protected BigDecimal discountRate;
  protected C1 offerBasedOnProduct;
  protected C2 offerAppliesToProduct;

  public Offer(BigDecimal discountRate) {
    this.discountRate = discountRate;
  }

  public <T extends Product> Function<T, BigDecimal> getDiscountFunction() {
    return product -> discountRate.multiply(product.getUnitPrice()).setScale(2);
  }

  protected abstract boolean isEligible(Product product, Collection<Product> products);

  public BigDecimal getDiscountedPrice(Product product, Collection<Product> products) {
    if (isEligible(product, products))
      return product.getUnitPrice().multiply(discountRate).setScale(2);
    else
      return product.getUnitPrice();
  }

  public Optional<EffectiveOffer> getEffectiveOffer(Product product, Collection<Product> products){
    if (isEligible(product, products))
      return Optional.of(new EffectiveOffer(getProductsOfferIsBasedOn(product, products), product, discountRate, product.getUnitPrice().subtract(getDiscountedPrice(product, products)).setScale(2)));
    else
      return Optional.empty();
  }

  abstract Collection<Product> getProductsOfferIsBasedOn(Product product, Collection<Product> products);

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Offer<?, ?> offer = (Offer<?, ?>) o;
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
