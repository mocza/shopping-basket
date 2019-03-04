package com.mocza.product;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.function.Supplier;

public class ProductFactory {

  public enum AvailableProducts {
    APPLE(() -> new Apple(new BigDecimal("1.0"))),
    BREAD(() -> new Bread(new BigDecimal("0.8"))),
    MILK(() -> new Milk(new BigDecimal("1.3"))),
    SOUP(() -> new Soup(new BigDecimal("0.65")));

    private Supplier<Product> supplier;

    AvailableProducts(Supplier<Product> supplier) {
      this.supplier = supplier;
    }

    public static Optional<Product> create(String productName) {
      try {
        return Optional.of(AvailableProducts.valueOf(productName.toUpperCase()).supplier.get());
      } catch (IllegalArgumentException | NullPointerException e) {
        return Optional.empty();
      }
    }
  }

  public static Optional<Product> create(String productName) {
    return AvailableProducts.create(productName);
  }

}
