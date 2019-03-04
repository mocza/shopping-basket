package com.mocza.offer;

import com.mocza.product.Apple;
import com.mocza.product.Product;

import java.math.BigDecimal;
import java.util.Collection;

import static java.util.Arrays.asList;

public class CheaperApplesOffer extends Offer {

  public CheaperApplesOffer(BigDecimal discountRate) {
    super(discountRate);
  }

  @Override
  protected boolean isEligible(Product product, Collection<Product> products) {
    return Apple.class.isInstance(product);
  }

  @Override
  Collection<Product> getProductsOfferIsBasedOn(Product product, Collection<Product> products) { return asList(product); }

}
