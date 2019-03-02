package com.mocza.basket;

import com.mocza.offer.EffectiveOffer;
import com.mocza.offer.Offer;
import com.mocza.product.Product;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;

public class PriceBasket {
  private Collection<Product> products;

  public PriceBasket(Collection<Product> products) {
    this.products = products;
  }

  public BigDecimal calculateSubtotal() {
    return products.stream().map(p -> p.getUnitPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  public BigDecimal calculateTotal(Collection<Offer> offers) {

    products.forEach(p -> p.setEffectiveOffer(getEffectiveOffer(p, offers)));
    return products.stream().map(p -> p.getDiscountedPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  private Optional<EffectiveOffer> getEffectiveOffer(Product product, Collection<Offer> offers) {
    return offers.stream().map(offer -> offer.getEffectiveOffer(product, products))
            .filter(optional -> optional.isPresent()).map(optional -> ((Optional<EffectiveOffer>)optional).get()).findFirst();
  }

  public Collection<Product> getProducts() {
    return products;
  }

}
