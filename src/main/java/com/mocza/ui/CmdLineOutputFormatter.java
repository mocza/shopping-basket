package com.mocza.ui;

import com.mocza.basket.Basket;
import com.mocza.offer.EffectiveOffer;
import com.mocza.offer.Offer;
import com.mocza.product.Product;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class CmdLineOutputFormatter implements OutputFormatter {
  private static final String NO_OFFERS_AVAILABLE = "(no offers available)";
  private static final String SUBTOTAL_ROW_FORMAT = "Subtotal: {0}{1}";
  private static final String TOTAL_ROW_FORMAT = "Total: {0}{1}";
  private static final String OFFER_ROW_FORMAT = "{0} {1}% off: -{2}";
  private Basket basket;

  public CmdLineOutputFormatter(Basket basket) {
    this.basket = basket;
  }

  public String getSubtotal() {
    return MessageFormat.format(SUBTOTAL_ROW_FORMAT,
            basket.getCurrency().getMajorCurrencySign(), String.valueOf(basket.calculateSubtotal()));
  }

  public Collection<String> getOffers() {
    List<String> formattedOffers = getFormattedOffers(getOffersGroupedByProduct(getEffectiveOffers(basket.getProducts())));
    return formattedOffers.isEmpty() ? asList(NO_OFFERS_AVAILABLE) : formattedOffers;
  }

  public String getTotal() {
    return MessageFormat.format(TOTAL_ROW_FORMAT,
            basket.getCurrency().getMajorCurrencySign(), String.valueOf(basket.calculateTotal()));
  }

  private Collection<EffectiveOffer> getEffectiveOffers(Collection<Product> products) {
    return products.stream()
        .filter(p -> p.getEffectiveOffer().isPresent())
        .map(p -> p.getEffectiveOffer().get())
        .collect(Collectors.toList());
  }

  private Map<Product, List<EffectiveOffer>> getOffersGroupedByProduct(Collection<EffectiveOffer> offers) {
    return offers.stream().collect(groupingBy(o -> o.getProductOfferIsAppliesTo()));
  }

  private List<String> getFormattedOffers(Map<Product, List<EffectiveOffer>> offersByProduct) {
    return offersByProduct.entrySet().stream()
              .map(entry ->
              MessageFormat.format(OFFER_ROW_FORMAT,
                      getFormattedProductName(entry.getKey(), entry.getValue().stream().count()),
                      getFormattedDiscountRate(entry.getValue().stream().map(EffectiveOffer::getDiscountRate).findFirst().get()),
                      getFormattedSavings(entry.getValue().stream().map(EffectiveOffer::getSavings).reduce(BigDecimal.ZERO, BigDecimal::add),
                              entry.getKey().getCurrency().getMajorCurrencySign(), entry.getKey().getCurrency().getMinorCurrencySign())))
              .collect(toList());
  }

  private BigDecimal getFormattedDiscountRate(BigDecimal discountRate) {
    return new BigDecimal("1.0").subtract(discountRate).multiply(new BigDecimal("100.0"));
  }

  private String getFormattedProductName(Product product, long count) {
    return MessageFormat.format("{0}:", StringUtils.capitalize(count > 1 ? product.getProductNamePlural() : product.getProductNameSingular()));
  }

  private String getFormattedSavings(BigDecimal savings, String majorCurrencySign, String minorCurrencySign) {
    return isLessThanOne(savings) ?
            MessageFormat.format("{0}{1}", String.valueOf(savings.multiply(new BigDecimal("100")).setScale(0)), minorCurrencySign) :
            MessageFormat.format("{0}{1}", String.valueOf(savings), majorCurrencySign);
  }

  private boolean isLessThanOne(BigDecimal savings) {
    return savings.compareTo(BigDecimal.ONE) <= 0;
  }

}
