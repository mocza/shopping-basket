import com.mocza.basket.Basket;
import com.mocza.offer.EffectiveOffer;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

public class BasketTest extends AbstractTest {
  private Basket basket;

  @Test
  public void calculateSubtotalOneItem() {
    basket = new Basket(asList(bread1));
    assertEquals(new BigDecimal("0.8"), basket.calculateSubtotal());
  }

  @Test
  public void calculateSubtotalTwoItems() {
    basket = new Basket(asList(bread1, milk));
    assertEquals(new BigDecimal("2.1"), basket.calculateSubtotal());
  }

  @Test
  public void calculateTotalOnOffer() {
    basket = new Basket(asList(apple1));
    assertEquals(new BigDecimal("0.90"), basket.calculateTotal());
    assertEquals(asList(Optional.of(new EffectiveOffer(apple1, asList(apple1), new BigDecimal("0.9"), new BigDecimal("0.10")))),
            basket.getProducts().stream().map(p -> p.getEffectiveOffer()).collect(toList()));
  }

  @Test
  public void calculateTotalNoOffer() {
    basket = new Basket(asList(bread1));
    assertEquals(new BigDecimal("0.8"), basket.calculateTotal());
    assertEquals(asList(Optional.empty()),
            basket.getProducts().stream().map(p -> p.getEffectiveOffer()).collect(toList()));
  }

  @Test
  public void calculateTotalTwoOnOfferSameProduct() {
    basket = new Basket(asList(apple1, apple2));
    assertEquals(new BigDecimal("1.80"), basket.calculateTotal());
    assertEquals(asList(
            Optional.of(new EffectiveOffer(apple1, asList(apple1), new BigDecimal("0.9"), new BigDecimal("0.10"))),
            Optional.of(new EffectiveOffer(apple2, asList(apple2), new BigDecimal("0.9"), new BigDecimal("0.10")))),
            basket.getProducts().stream().map(p -> p.getEffectiveOffer()).collect(toList()));
  }

  @Test
  public void calculateTotalTwoOnOfferOneNotOnOffer() {
    basket = new Basket(asList(apple1, apple2, bread1));
    assertEquals(new BigDecimal("2.60"), basket.calculateTotal());
    assertEquals(asList(
            Optional.of(new EffectiveOffer(apple1, asList(apple1), new BigDecimal("0.9"), new BigDecimal("0.10"))),
            Optional.of(new EffectiveOffer(apple2, asList(apple2), new BigDecimal("0.9"), new BigDecimal("0.10"))),
            Optional.empty()),
            basket.getProducts().stream().map(p -> p.getEffectiveOffer()).collect(toList()));
  }

  @Test
  public void calculateTotal_oneBreadHalfPriceOffer() {
    basket = new Basket(asList(soup1, soup2, bread1));
    assertEquals(new BigDecimal("1.70"), basket.calculateTotal());
    assertEquals(asList(
            Optional.empty(),
            Optional.empty(),
            Optional.of(new EffectiveOffer(bread1, asList(soup1, soup2), new BigDecimal("0.5"), new BigDecimal("0.40")))),
            basket.getProducts().stream().map(p -> p.getEffectiveOffer()).collect(toList()));
  }

  @Test
  public void calculateTotal_breadFullPriceWithOneSoup() {
    basket = new Basket(asList(soup1, bread1));
    assertEquals(new BigDecimal("1.45"), basket.calculateTotal());
    assertEquals(asList(
            Optional.empty(),
            Optional.empty()),
            basket.getProducts().stream().map(p -> p.getEffectiveOffer()).collect(toList()));
  }

  @Test
  public void calculateTotal_twoBreadsHalfPriceWithFourSoupsOffer() {
    basket = new Basket(asList(soup1, soup2, soup3, soup4, bread1, bread2));
    assertEquals(new BigDecimal("3.40"), basket.calculateTotal());
    assertEquals(asList(
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.of(new EffectiveOffer(bread1, asList(soup1, soup2), new BigDecimal("0.5"), new BigDecimal("0.40"))),
            Optional.of(new EffectiveOffer(bread2, asList(soup1, soup2), new BigDecimal("0.5"), new BigDecimal("0.40")))),
            basket.getProducts().stream().map(p -> p.getEffectiveOffer()).collect(toList()));
  }

}
