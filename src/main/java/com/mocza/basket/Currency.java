package com.mocza.basket;

public enum Currency {
  GBP("\u00A3", "p");

  private String majorCurrencySign;
  private String minorCurrencySign;

  Currency(String majorCurrencySign, String minorCurrencySign) {
    this.majorCurrencySign = majorCurrencySign;
    this.minorCurrencySign = minorCurrencySign;
  }

  public String getMajorCurrencySign() { return majorCurrencySign; }

  public String getMinorCurrencySign() { return minorCurrencySign; }
}
