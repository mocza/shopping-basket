package com.mocza.ui;

import java.math.BigDecimal;
import java.util.Collection;

public interface OutputFormatter {
  String getSubtotal();
  Collection<String> getOffers();
  String getTotal();
  BigDecimal getFormattedPrice(BigDecimal price);
}
