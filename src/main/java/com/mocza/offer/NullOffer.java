package com.mocza.offer;

import com.mocza.product.Product;

import java.math.BigDecimal;
import java.util.Collection;

public class NullOffer extends Offer {
  public static final Offer NULL_OFFER = new NullOffer();

  public NullOffer() {
    super(BigDecimal.ONE);
  }

  @Override
  protected boolean isEligible(Product product, Collection products) {
    return false;
  }


  @Override
  Collection<Product> getProductsOfferIsBasedOn(Product product, Collection collection) {
    return null;
  }
}
