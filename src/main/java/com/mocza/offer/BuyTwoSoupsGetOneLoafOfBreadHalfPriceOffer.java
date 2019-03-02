package com.mocza.offer;

import com.mocza.product.Bread;
import com.mocza.product.Product;
import com.mocza.product.Soup;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.stream.Collectors;

public class BuyTwoSoupsGetOneLoafOfBreadHalfPriceOffer extends Offer {

  public BuyTwoSoupsGetOneLoafOfBreadHalfPriceOffer() {
    super(new BigDecimal("0.5"));
  }

  @Override
  protected boolean isEligible(Product product, Collection products) {
    return Bread.class.isInstance(product) && (products.stream().filter(p -> Soup.class.isInstance(p)).count() == 2);
  }

  @Override
  Collection<Product> getProductsOfferIsBasedOn(Product product, Collection collection) {
    Collection<Product>  products = collection;
    return products.stream().filter(p -> Soup.class.isInstance(p)).collect(Collectors.toList());
  }


}
