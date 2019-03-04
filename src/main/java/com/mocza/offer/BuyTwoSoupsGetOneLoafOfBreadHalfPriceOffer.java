package com.mocza.offer;

import com.mocza.product.Bread;
import com.mocza.product.Product;
import com.mocza.product.Soup;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

public class BuyTwoSoupsGetOneLoafOfBreadHalfPriceOffer extends Offer<Bread> {

  public BuyTwoSoupsGetOneLoafOfBreadHalfPriceOffer() {
    super(new BigDecimal("0.5"));
  }

  @Override
  protected boolean isEligible(Bread product, Collection<Product> products) {
    return products.stream().filter(p -> Soup.class.isInstance(p) && !getSoupsAlreadyUsedInAnOffer(products).contains(p)).count() >= 2;
  }

  @Override
  Collection<Product> getProductsOfferIsBasedOn(Bread product, Collection<Product> products) {
    return products.stream().filter(p -> Soup.class.isInstance(p) && !getSoupsAlreadyUsedInAnOffer(products).contains(p))
            .limit(2)
            .collect(toList());
  }

  private Collection<Soup> getSoupsAlreadyUsedInAnOffer(Collection<Product> products) {
    return products.stream()
            .filter(p -> Bread.class.isInstance(p))
            .filter(p -> ((Bread) p).getEffectiveOffer().isPresent()
                    && BuyTwoSoupsGetOneLoafOfBreadHalfPriceOffer.class.isInstance(((Bread) p).getEffectiveOffer().get()))
            .map(p -> ((Bread) p).getEffectiveOffer().get().getProductOfferIsBasedOn())
            .flatMap(l -> l.stream())
            .filter(Objects::nonNull)
            .map(Soup.class::cast)
            .collect(toList());
  }


}
