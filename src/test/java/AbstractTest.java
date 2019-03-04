import com.mocza.offer.BuyTwoSoupsGetOneLoafOfBreadHalfPriceOffer;
import com.mocza.offer.CheaperApplesOffer;
import com.mocza.product.*;

import java.math.BigDecimal;

public abstract class AbstractTest {
  protected Bread bread1 = (Bread)ProductFactory.create("bread");
  protected Bread bread2 = (Bread)ProductFactory.create("bread");
  protected Bread bread3 = (Bread)ProductFactory.create("bread");
  protected Milk milk = (Milk)ProductFactory.create("milk");
  protected Apple apple1 = (Apple)ProductFactory.create("apple");
  protected Apple apple2 = (Apple)ProductFactory.create("apple");
  protected Soup soup1 = (Soup)ProductFactory.create("soup");
  protected Soup soup2 = (Soup)ProductFactory.create("soup");
  protected Soup soup3 = (Soup)ProductFactory.create("soup");
  protected Soup soup4 = (Soup)ProductFactory.create("soup");
  protected Soup soup5 = (Soup)ProductFactory.create("soup");
  protected Soup soup6 = (Soup)ProductFactory.create("soup");
  protected CheaperApplesOffer cheaperApplesOffer = new CheaperApplesOffer(new BigDecimal("0.9"));
  protected BuyTwoSoupsGetOneLoafOfBreadHalfPriceOffer buyTwoSoupsGetOneLoafOfBreadHalfPriceOffer = new BuyTwoSoupsGetOneLoafOfBreadHalfPriceOffer();


}
