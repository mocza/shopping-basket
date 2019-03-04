package com.mocza.basket;

public enum Unit {
  LOAF("loaf"), TIN("tin"), BOTTLE("bottle"), BAG("bag");

  private String displayName;

  Unit(String displayName) {
    this.displayName = displayName;
  }

  public String getDisplayName() {
    return displayName;
  }
}
