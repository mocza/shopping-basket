package com.mocza.product;

import com.mocza.offer.BuyTwoSoupsGetOneLoafOfBreadHalfPriceOffer;
import com.mocza.offer.CheaperApplesOffer;
import com.mocza.offer.Offer;

import java.math.BigDecimal;
import java.util.function.Supplier;

public class ProductFactory {

  public enum AvailableProducts {
    APPLE(() -> new Apple(new BigDecimal("1.0")).withOffer((Offer)new CheaperApplesOffer(new BigDecimal("0.9")))),
    BREAD(() -> new Bread(new BigDecimal("0.8")).withOffer((Offer)new BuyTwoSoupsGetOneLoafOfBreadHalfPriceOffer())),
    MILK(() -> new Milk(new BigDecimal("1.3"))),
    SOUP(() -> new Soup(new BigDecimal("0.65")));

    private Supplier<Product> supplier;

    AvailableProducts(Supplier<Product> supplier) {
      this.supplier = supplier;
    }

    public static Product create(String productName) {
        return AvailableProducts.valueOf(productName.toUpperCase()).supplier.get();
    }
  }

  public static Product create(String productName) {
    return AvailableProducts.create(productName);
  }

}
