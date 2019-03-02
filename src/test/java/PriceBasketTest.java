import com.mocza.basket.Currency;
import com.mocza.basket.PriceBasket;
import com.mocza.basket.Unit;
import com.mocza.offer.BuyTwoSoupsGetOneLoafOfBreadHalfPriceOffer;
import com.mocza.offer.CheaperApplesOffer;
import com.mocza.offer.EffectiveOffer;
import com.mocza.offer.Offer;
import com.mocza.product.Apple;
import com.mocza.product.Bread;
import com.mocza.product.Milk;
import com.mocza.product.Soup;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

public class PriceBasketTest {

  private Bread bread1;
  private Bread bread2;
  private Milk milk;
  private PriceBasket priceBasket;
  private Apple apple1;
  private Apple apple2;
  private Soup soup1;
  private Soup soup2;
  private Soup soup3;
  private Soup soup4;
  private CheaperApplesOffer cheaperApplesOffer;
  private BuyTwoSoupsGetOneLoafOfBreadHalfPriceOffer buyTwoSoupsGetOneLoafOfBreadHalfPriceOffer;
  private Collection<Offer> offers;

  @Before
  public void setUp() throws Exception {
    cheaperApplesOffer = new CheaperApplesOffer(new BigDecimal("0.9"));
    buyTwoSoupsGetOneLoafOfBreadHalfPriceOffer = new BuyTwoSoupsGetOneLoafOfBreadHalfPriceOffer();
    offers = asList(cheaperApplesOffer, buyTwoSoupsGetOneLoafOfBreadHalfPriceOffer);

    bread1 = new Bread(Unit.LOAF, new BigDecimal("0.8"), Currency.GBP);
    bread2 = new Bread(Unit.LOAF, new BigDecimal("0.8"), Currency.GBP);
    milk = new Milk(Unit.BOTTLE, new BigDecimal("1.30"), Currency.GBP);
    apple1 = new Apple(Unit.BAG, new BigDecimal("1.0"), Currency.GBP);
    apple2 = new Apple(Unit.BAG, new BigDecimal("1.0"), Currency.GBP);
    soup1 = new Soup(Unit.TIN, new BigDecimal("0.65"), Currency.GBP);
    soup2 = new Soup(Unit.TIN, new BigDecimal("0.65"), Currency.GBP);
    soup3 = new Soup(Unit.TIN, new BigDecimal("0.65"), Currency.GBP);
    soup4 = new Soup(Unit.TIN, new BigDecimal("0.65"), Currency.GBP);
  }

  @Test
  public void calculateSubtotalOneItem() {
    priceBasket = new PriceBasket(asList(bread1));
    assertEquals(new BigDecimal("0.8"), priceBasket.calculateSubtotal());
  }

  @Test
  public void calculateSubtotalTwoItems() {
    priceBasket = new PriceBasket(asList(bread1, milk));
    assertEquals(new BigDecimal("2.10"), priceBasket.calculateSubtotal());
  }

  @Test
  public void calculateTotalOnOffer() {
    priceBasket = new PriceBasket(asList(apple1));
    assertEquals(new BigDecimal("0.90"), priceBasket.calculateTotal(offers));
    assertEquals(asList(Optional.of(new EffectiveOffer(asList(apple1), apple1, new BigDecimal("0.9"), new BigDecimal("0.10")))),
            priceBasket.getProducts().stream().map(p -> p.getEffectiveOffer()).collect(toList()));
  }

  @Test
  public void calculateTotalNoOffer() {
    priceBasket = new PriceBasket(asList(bread1));
    assertEquals(new BigDecimal("0.8"), priceBasket.calculateTotal(offers));
    assertEquals(asList(Optional.empty()),
            priceBasket.getProducts().stream().map(p -> p.getEffectiveOffer()).collect(toList()));
  }

  @Test
  public void calculateTotalTwoOnOfferSameProduct() {
    priceBasket = new PriceBasket(asList(apple1, apple2));
    assertEquals(new BigDecimal("1.80"), priceBasket.calculateTotal(offers));
    assertEquals(asList(
            Optional.of(new EffectiveOffer(asList(apple1), apple1, new BigDecimal("0.9"), new BigDecimal("0.10"))),
            Optional.of(new EffectiveOffer(asList(apple2), apple2, new BigDecimal("0.9"), new BigDecimal("0.10")))),
            priceBasket.getProducts().stream().map(p -> p.getEffectiveOffer()).collect(toList()));
  }

  @Test
  public void calculateTotalTwoOnOfferOneNotOnOffer() {
    priceBasket = new PriceBasket(asList(apple1, apple2, bread1));
    assertEquals(new BigDecimal("2.60"), priceBasket.calculateTotal(offers));
    assertEquals(asList(
            Optional.of(new EffectiveOffer(asList(apple1), apple1, new BigDecimal("0.9"), new BigDecimal("0.10"))),
            Optional.of(new EffectiveOffer(asList(apple2), apple2, new BigDecimal("0.9"), new BigDecimal("0.10"))),
            Optional.empty()),
            priceBasket.getProducts().stream().map(p -> p.getEffectiveOffer()).collect(toList()));
  }


  @Test
  public void calculateTotal_oneBreadHalfPriceOffer() {
    priceBasket = new PriceBasket(asList(soup1, soup2, bread1));
    assertEquals(new BigDecimal("1.70"), priceBasket.calculateTotal(offers));
    assertEquals(asList(
            Optional.empty(),
            Optional.empty(),
            Optional.of(new EffectiveOffer(asList(soup1, soup2), bread1, new BigDecimal("0.5"), new BigDecimal("0.40")))),
            priceBasket.getProducts().stream().map(p -> p.getEffectiveOffer()).collect(toList()));
  }

  @Test
  public void calculateTotal_breadFullPriceWithOneSoup() {
    priceBasket = new PriceBasket(asList(soup1, bread1));
    assertEquals(new BigDecimal("1.45"), priceBasket.calculateTotal(offers));
    assertEquals(asList(
            Optional.empty(),
            Optional.empty()),
            priceBasket.getProducts().stream().map(p -> p.getEffectiveOffer()).collect(toList()));
  }

  @Test
  public void calculateTotal_twoBreadsHalfPriceWithFourSoupsOffer() {
    priceBasket = new PriceBasket(asList(soup1, soup2, soup3, soup4, bread1, bread2));
    assertEquals(new BigDecimal("3.40"), priceBasket.calculateTotal(offers));
    assertEquals(asList(
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.of(new EffectiveOffer(asList(soup1, soup2), bread1, new BigDecimal("0.5"), new BigDecimal("0.40"))),
            Optional.of(new EffectiveOffer(asList(soup1, soup2), bread2, new BigDecimal("0.5"), new BigDecimal("0.40")))),
            priceBasket.getProducts().stream().map(p -> p.getEffectiveOffer()).collect(toList()));
  }


}
