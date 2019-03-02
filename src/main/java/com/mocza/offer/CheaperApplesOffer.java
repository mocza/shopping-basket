package com.mocza.offer;

import com.mocza.offer.Offer;
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
  Collection<Product> getProductsOfferIsBasedOn(Product product, Collection collection) {
    return asList(product);
  }


  @Override
  protected boolean isEligible(Product product, Collection products) {
    return Apple.class.isInstance(product);
  }


}
