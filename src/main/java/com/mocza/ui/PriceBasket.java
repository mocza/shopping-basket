package com.mocza.ui;

import com.mocza.basket.Basket;
import com.mocza.offer.BuyTwoSoupsGetOneLoafOfBreadHalfPriceOffer;
import com.mocza.offer.CheaperApplesOffer;
import com.mocza.offer.Offer;
import com.mocza.product.Product;
import com.mocza.product.ProductFactory;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class PriceBasket {

  public static void main(String[] args) {
    if (args.length < 1) printUsageAndExit();
    Collection<Product> products = getProducts(args);
    printReceipt(new CmdLineOutputFormatter(new Basket(products)));
  }

  private static void printReceipt(CmdLineOutputFormatter cmdLineOutputFormatter) {
    System.out.println(cmdLineOutputFormatter.getSubtotal());
    String total = cmdLineOutputFormatter.getTotal();
    System.out.println(getOffers(cmdLineOutputFormatter));
    System.out.println(total);
  }

  private static String getOffers(CmdLineOutputFormatter cmdLineOutputFormatter) {
    return cmdLineOutputFormatter.getOffers().stream().collect(Collectors.joining("\n"));
  }

  private static Collection<Product> getProducts(String[] productNames) {
    return Arrays.asList(productNames).stream().map(productName -> ProductFactory.create(productName)).collect(toList());
  }

  private static void printUsageAndExit() {
    System.out.println(MessageFormat.format("Usage: {0} <item1> <item2>...", PriceBasket.class.getSimpleName()));
    System.out.println(MessageFormat.format("Example: {0} Apple Milk Bread Soup Soup", PriceBasket.class.getSimpleName()));
    System.out.println(MessageFormat.format("Available products are {0}, product names are case insensitive!", asList(ProductFactory.AvailableProducts.values())));
    System.exit(1);
  }

}
