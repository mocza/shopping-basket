package com.mocza.ui;

import com.mocza.offer.Offer;

import java.util.Collection;

public interface OutputFormatter {
  String getSubtotal();
  Collection<String> getOffers();
  String getTotal(Collection<Offer> offers);
}
