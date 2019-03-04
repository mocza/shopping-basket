import com.mocza.basket.Currency;
import com.mocza.basket.Unit;
import com.mocza.offer.BuyTwoSoupsGetOneLoafOfBreadHalfPriceOffer;
import com.mocza.offer.CheaperApplesOffer;
import com.mocza.product.Apple;
import com.mocza.product.Bread;
import com.mocza.product.Milk;
import com.mocza.product.Soup;

import java.math.BigDecimal;

public class ShoppingTest {
  protected Bread bread1 = new Bread(Unit.LOAF, new BigDecimal("0.8"), Currency.GBP);
  protected Bread bread2 = new Bread(Unit.LOAF, new BigDecimal("0.8"), Currency.GBP);
  protected Bread bread3 = new Bread(Unit.LOAF, new BigDecimal("0.8"), Currency.GBP);
  protected Milk milk = new Milk(Unit.BOTTLE, new BigDecimal("1.30"), Currency.GBP);
  protected Apple apple1 = new Apple(Unit.BAG, new BigDecimal("1.0"), Currency.GBP);
  protected Apple apple2 = new Apple(Unit.BAG, new BigDecimal("1.0"), Currency.GBP);
  protected Soup soup1 = new Soup(Unit.TIN, new BigDecimal("0.65"), Currency.GBP);
  protected Soup soup2 = new Soup(Unit.TIN, new BigDecimal("0.65"), Currency.GBP);
  protected Soup soup3 = new Soup(Unit.TIN, new BigDecimal("0.65"), Currency.GBP);
  protected Soup soup4 = new Soup(Unit.TIN, new BigDecimal("0.65"), Currency.GBP);
  protected Soup soup5 = new Soup(Unit.TIN, new BigDecimal("0.65"), Currency.GBP);
  protected Soup soup6 = new Soup(Unit.TIN, new BigDecimal("0.65"), Currency.GBP);
  protected CheaperApplesOffer cheaperApplesOffer = new CheaperApplesOffer(new BigDecimal("0.9"));
  protected BuyTwoSoupsGetOneLoafOfBreadHalfPriceOffer buyTwoSoupsGetOneLoafOfBreadHalfPriceOffer = new BuyTwoSoupsGetOneLoafOfBreadHalfPriceOffer();


}
