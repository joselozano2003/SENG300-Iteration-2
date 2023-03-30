package com.autovend.software.test;

import static org.junit.Assert.*;
import java.math.BigDecimal;
import org.junit.*;
import com.autovend.*;
import com.autovend.devices.OverloadException;
import com.autovend.devices.SimulationException;
import com.autovend.products.BarcodedProduct;
import com.autovend.software.*;
public class BagTest {
	Bag bag;
	//sets up a Bag for testing
	@Before
	public void setup() {
		Numeral numeral = Numeral.valueOf("one");
		Barcode barcode = new Barcode(numeral);
		String string = "test bag";
		BigDecimal price = BigDecimal.valueOf(0.5);
		double weight = 0.1;
		boolean purchased = true;
		bag = new Bag(barcode, string, price, weight, purchased);
	}
	@After
	public void tearDown() {
		bag = null;
	}
	//tests if the bag was constructed correctly
	@Test
	public void bagConstructorTest1() {
		assertEquals(Numeral.valueOf("one"), bag.getBarcode().getDigitAt(0));
		assertEquals(bag.getDescription(), "test bag");
		assertEquals(bag.getPrice(), BigDecimal.valueOf(0.5));
		assertEquals(bag.getExpectedWeight(), 0.1, 0.0);
		assertTrue(bag.gettype());
	}
	//tests if an item with too much weight correctly throws an exception
	@Test
	public void addItemTest1() {
		boolean failed = false;
		PurchasedItems item = new PurchasedItems();
		Numeral num = Numeral.valueOf("two");
		Barcode bar = new Barcode(num);
		String str = "test item";
		BigDecimal price = BigDecimal.valueOf(0.5);
		double weight = 100000;
		BarcodedProduct product = new BarcodedProduct(bar,str,price,weight);
		item.addProduct(product);
		try {
			bag.additem(item);
		} catch (SimulationException e) {
			failed = true;
		} catch (OverloadException e) {}
		assertTrue(failed);
	}
	//tests if an item is added correctly
	@Test
	public void addItemTest2() {
		PurchasedItems item = new PurchasedItems();
		Numeral num = Numeral.valueOf("three");
		Barcode bar = new Barcode(num);
		String str = "test item";
		BigDecimal price = BigDecimal.valueOf(0.5);
		double weight = 10;
		BarcodedProduct product = new BarcodedProduct(bar,str,price,weight);
		item.addProduct(product);
		try {
			bag.additem(item);
		} catch (OverloadException e) {}
		assertEquals(bag.baggage.get(0).getTotalExpectedWeight(), 10, 0);
	}
	//tests if an exception is thrown when an item is attempted to be removed from an empty bag
	@Test
	public void removeItemTest1() {
		boolean failed = false;
		try {
			bag.removeitem(null);
		}catch(SimulationException e) {
			failed = true;
		}
		assertTrue(failed);
	}
	//tests if an item is removed correctly
	@Test
	public void removeItemTest2() {
		PurchasedItems item = new PurchasedItems();
		Numeral num = Numeral.valueOf("four");
		Barcode bar = new Barcode(num);
		String str = "test item";
		BigDecimal price = BigDecimal.valueOf(0.5);
		double weight = 10;
		BarcodedProduct product = new BarcodedProduct(bar,str,price,weight);
		item.addProduct(product);
		try {
			bag.additem(item);
		} catch (OverloadException e) {}
		bag.removeitem(item);
		assertTrue(bag.baggage.isEmpty());
	}
	//tests if an exception is thrown when a bag is attempted to be emptied from an empty bag
	@Test
	public void emptyBagTest1() {
		boolean failed = false;
		try {
			bag.emptyBag();
		}catch(SimulationException e) {
			failed = true;
		}
		assertTrue(failed);
	}
	//tests if a bag is emptied correctly
		@Test
		public void emptyBagTest2() {
			PurchasedItems item = new PurchasedItems();
			Numeral num = Numeral.valueOf("five");
			Barcode bar = new Barcode(num);
			String str = "test item";
			BigDecimal price = BigDecimal.valueOf(0.5);
			double weight = 10;
			BarcodedProduct product = new BarcodedProduct(bar,str,price,weight);
			item.addProduct(product);
			try {
				bag.additem(item);
			} catch (OverloadException e) {}
			bag.emptyBag();
			assertTrue(bag.baggage.isEmpty());
		}
	
	
	
	
}
