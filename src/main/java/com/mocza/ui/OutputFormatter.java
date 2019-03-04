package com.mocza.ui;

import java.util.Collection;

public interface OutputFormatter {
  String getSubtotal();
  Collection<String> getOffers();
  String getTotal();
}
