package com.mocza.offer;

import com.mocza.product.Bread;
import com.mocza.product.Product;
import com.mocza.product.Soup;

import java.math.BigDecimal;
import java.util.Collection;

import static java.util.stream.Collectors.toList;

public class BuyTwoSoupsGetOneLoafOfBreadHalfPriceOffer extends Offer<Bread> {

  public BuyTwoSoupsGetOneLoafOfBreadHalfPriceOffer() {
    super(new BigDecimal("0.5"));
  }

  @Override
  protected boolean isEligible(Bread product, Collection<Product> products) {
    return products.stream()
            .filter(Soup.class::isInstance)
            .filter(p -> !getSoupsAlreadyUsedInOffer(products).contains(p))
            .count() >= 2;
  }

  @Override
  Collection<Product> getProductsOfferIsBasedOn(Bread product, Collection<Product> products) {
    return products.stream().filter(Soup.class::isInstance)
            .filter(p -> !getSoupsAlreadyUsedInOffer(products).contains(p))
            .limit(2)
            .collect(toList());
  }

  private Collection<Soup> getSoupsAlreadyUsedInOffer(Collection<Product> products) {
    return products.stream()
            .filter(Bread.class::isInstance)
            .map(Bread.class::cast)
            .filter(bread -> bread.getEffectiveOffer().isPresent())
            .filter(bread -> BuyTwoSoupsGetOneLoafOfBreadHalfPriceOffer.class.isInstance(bread.getEffectiveOffer().get()))
            .map(bread -> bread.getEffectiveOffer().get().getOfferIsBasedOn())
            .flatMap(Collection::stream)
            .map(Soup.class::cast)
            .collect(toList());
  }

}
