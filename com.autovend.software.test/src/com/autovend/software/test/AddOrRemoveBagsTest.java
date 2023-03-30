package com.autovend.software.test;


import static org.junit.Assert.*;
import java.math.BigDecimal;
import org.junit.*;
import com.autovend.*;
import com.autovend.devices.OverloadException;
import com.autovend.devices.SelfCheckoutStation;
import com.autovend.devices.SimulationException;
import com.autovend.products.BarcodedProduct;
import com.autovend.software.*;
public class AddOrRemoveBagsTest {
	Bag bag;
	AddOrRemoveBags test;
	SelfCheckoutStation scs;
	//sets up for testing
	@Before
	public void setup() {
		Numeral numeral = Numeral.valueOf("one");
		Barcode barcode = new Barcode(numeral);
		String string = "test bag";
		BigDecimal price = BigDecimal.valueOf(0.5);
		double weight = 0.1;
		boolean purchased = true;
		bag = new Bag(barcode, string, price, weight, purchased);
		test = new AddOrRemoveBags(scs);
	}
	@After
	public void tearDown() {
		bag = null;
		test = null;
	}
	//tests if the class is constructed correctly
	@Test
	public void AddOrRemoveBagsConstructorTest1() {
		test = new AddOrRemoveBags(scs);
		assertTrue(test.bags.isEmpty());
		assertTrue(test.bagsremaining.isEmpty());
		assertEquals(test.storebags, 0);
		assertEquals(test.ownbags, 0);
		assertNotNull(test.purchase);
	}
	//tests if a bag is added correctly
	@Test
	public void AddOrRemoveBagsAddStoreBagsTest1() {
		test.addStoreBag(bag);
		assertEquals(test.bagsremaining.get(0), bag);
	}
	//tests if a null bag correctly throws an exception
	@Test
	public void AddOrRemoveBagsAddStoreBagsTest2() {
		boolean failed = false;
		try {
			test.addStoreBag(null);
		}catch(SimulationException e) {
			failed = true;
		}
		assertTrue(failed);
	}
	//tests if a bag can be correctly purchased
	@Test
	public void AddOrRemoveBagsPurchaseBagTest1() {
		test.addStoreBag(bag);
		test.purchaseBag();
		assertEquals(test.bags.get(0), bag);
		assertEquals(test.storebags, 1);
	}
	//tests if an exception is correctly thrown when a bag is attempted to be purchased, but there are no bags remaining
	@Test
	public void AddOrRemoveBagsPurchaseBagTest2() {
		boolean failed = false;
		try {
			test.purchaseBag();
		}catch(SimulationException e) {
			failed = true;
		}
		assertTrue(failed);
	}
	//tests if an exception is correctly thrown when a bag is attempted to be purchased, but there are too many bags
	@Test
	public void AddOrRemoveBagsPurchaseBagTest3() {
		boolean failed = false;
		test.storebags = Integer.MAX_VALUE;
		try {
			test.purchaseBag();
		}catch(SimulationException e) {
			failed = true;
		}
		assertTrue(failed);
	}
	//tests if a personal bag can be correctly added
	@Test
	public void AddOrRemoveBagsAddOwnBagTest1() {
		Barcode zerocode = new Barcode(Numeral.valueOf("zero"));
		test.addOwnBag("Test bag", 0.05);
		assertEquals(test.bags.get(0).getBarcode(), zerocode);
		assertEquals(test.bags.get(0).getDescription(), "Test bag");
		assertEquals(test.bags.get(0).getPrice(), BigDecimal.valueOf(0.00000000000001));
		assertEquals(test.bags.get(0).getExpectedWeight(), 0.05, 0);
		assertEquals(test.ownbags, 1);
	}
	//tests if an exception is correctly thrown when a personal bag is attempted to be purchased, but there are too many bags
	@Test
	public void AddOrRemoveBagsAddOwnBagTest2() {
		boolean failed = false;
		test.ownbags = Integer.MAX_VALUE;
		try {
			test.purchaseBag();
		}catch(SimulationException e) {
			failed = true;
		}
		assertTrue(failed);
	}
	//tests if a store bag can be correctly removed
	@Test
	public void AddOrRemoveBagsRemoveBagTest1() {
		test.addStoreBag(bag);
		test.purchaseBag();
		test.removeBag(bag);
		assertTrue(test.bags.isEmpty());
		assertEquals(test.storebags, 0);
	}
	//tests if an exception is correctly thrown when a non-empty store bag is attempted to be removed
	@Test
	public void AddOrRemoveBagsRemoveBagTest2() {
		boolean failed = false;
		test.addStoreBag(bag);
		test.purchaseBag();
		PurchasedItems item = new PurchasedItems();
		try {
			test.bags.get(0).additem(item);
			test.removeBag(bag);
		} catch (OverloadException e) {}
		catch(SimulationException e) {
			failed = true;
		}
		assertTrue(failed);
		assertEquals(test.storebags, 1);
	}
	//tests if a personal bag can be correctly removed
	@Test
	public void AddOrRemoveBagsRemoveBagTest3() {
		test.addOwnBag("test bag", 1);
		test.removeBag(test.bags.get(0));
		assertTrue(test.bags.isEmpty());
		assertEquals(test.ownbags, 0);
	}
	//tests if an exception is correctly thrown when a non-empty personal bag is attempted to be removed
	@Test
	public void AddOrRemoveBagsRemoveBagTest4() {
		boolean failed = false;
		test.addOwnBag("test bag", 1);
		PurchasedItems item = new PurchasedItems();
		try {
			test.bags.get(0).additem(item);
			test.removeBag(test.bags.get(0));
		} catch (OverloadException e) {}
		catch(SimulationException e) {
			failed = true;
		}
		assertTrue(failed);
		assertEquals(test.ownbags, 1);
	}
	//tests if an exception is thrown if a null bag is attempted to be removed
	@Test
	public void AddOrRemoveBagsRemoveBagTest5() {
		boolean failed = false;
		try {
			test.removeBag(null);
		}catch(Exception e) {
			failed = true;
		}
		assertTrue(failed);
	}







}
