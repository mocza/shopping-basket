import com.mocza.basket.Basket;
import com.mocza.ui.CmdLineOutputFormatter;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class CmdLineOutputFormatterTest extends AbstractTest {

  @Test
  public void subtotal() {
    Basket basket = new Basket(asList(bread1));
    assertEquals("Subtotal: \u00A30.80", new CmdLineOutputFormatter(basket).getSubtotal());
  }

  @Test
  public void totalWithNoOffer() {
    Basket basket = new Basket(asList(milk));
    assertEquals("Total: \u00A31.30", new CmdLineOutputFormatter(basket).getTotal());
    assertEquals(asList("(no offers available)"), new CmdLineOutputFormatter(basket).getOffers());
  }

  @Test
  public void totalWithAppleOnOffer() {
    Basket basket = new Basket(asList(apple1));
    assertEquals("Total: \u00A30.90", new CmdLineOutputFormatter(basket).getTotal());
    assertEquals(asList("Apple: 10% off: -10p"), new CmdLineOutputFormatter(basket).getOffers());
  }

  @Test
  public void totalWithTwoApplesOnOffer() {
    Basket basket = new Basket(asList(apple1, apple2));
    assertEquals("Total: \u00A31.80", new CmdLineOutputFormatter(basket).getTotal());
    assertEquals(asList("Apples: 10% off: -20p"), new CmdLineOutputFormatter(basket).getOffers());
  }

  @Test
  public void totalWithOneBreadBoughtWithTwoSoupsOnOffer() {
    Basket basket = new Basket(asList(soup1, soup2, bread1));
    assertEquals("Total: \u00A31.70", new CmdLineOutputFormatter(basket).getTotal());
    assertEquals(asList("Bread: 50% off: -40p"), new CmdLineOutputFormatter(basket).getOffers());
  }

  @Test
  public void totalWithTwoLoafsOfBreadBoughtWithFourSoupsOnOffer() {
    Basket basket = new Basket(asList(soup1, soup2, bread1, soup3, soup4, bread2));
    assertEquals("Total: \u00A33.40", new CmdLineOutputFormatter(basket).getTotal());
    assertEquals(asList("Loafs of bread: 50% off: -80p"), new CmdLineOutputFormatter(basket).getOffers());
  }

  @Test
  public void totalWithOver1PoundSavings() {
    Basket basket = new Basket(asList(soup1, soup2, bread1, soup3, soup4, bread2, soup5, soup6, bread3));
    assertEquals("Total: \u00A35.10", new CmdLineOutputFormatter(basket).getTotal());
    assertEquals(asList("Loafs of bread: 50% off: -1.20\u00A3"), new CmdLineOutputFormatter(basket).getOffers());
  }

}
