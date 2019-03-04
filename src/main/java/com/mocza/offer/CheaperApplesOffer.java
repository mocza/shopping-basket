package com.mocza.offer;

import com.mocza.product.Apple;
import com.mocza.product.Product;

import java.math.BigDecimal;
import java.util.Collection;

import static java.util.Arrays.asList;

public class CheaperApplesOffer extends Offer<Apple> {

  public CheaperApplesOffer(BigDecimal discountRate) {
    super(discountRate);
  }

  @Override
  protected boolean isEligible(Apple apple, Collection<Product> products) { return true; }

  @Override
  Collection<Product> getProductsOfferIsBasedOn(Apple apple, Collection<Product> products) { return asList(apple); }

}
