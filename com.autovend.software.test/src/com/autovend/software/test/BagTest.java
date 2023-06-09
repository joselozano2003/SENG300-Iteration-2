/** Members for Iteration 2:
 * Ethan Oke (30142180)
 * Jose Camilo Lozano Cetina (30144736)
 * Quinn Leonard (30145315)
 * Efren Garcia (30146181)
 * Nam Anh Vu (30127597)
 * Tyler Nguyen (30158563)
 * Victor Han (30112492)
 * Francisco Huayhualla (30091238)
 * Md Minhazur Rahman Hamim (30145446)
 * Imran Haji (30141571)
 * Sara Dhuka (30124117)
 * Robert (William) Engel (30119608)
 */
package com.autovend.software.test;

import static org.junit.Assert.*;
import java.math.BigDecimal;
import org.junit.*;
import com.autovend.devices.SimulationException;
import com.autovend.software.*;
public class BagTest {
	Bag bag;
    Bag reusableBagSmall;
    Bag reusableBagMedium;
    Bag reusableBagLarge;

    Bag disposableBagSmall;
    Bag disposableBagMedium;
    Bag disposableBagLarge;
	//sets up a Bag for testing
	@Before
	public void setup() {
        reusableBagSmall = new Bag("Small Reusable Bag", new BigDecimal("0.25"), 0.1);
        reusableBagMedium = new Bag("Medium Reusable Bag", new BigDecimal("0.50"), 0.2);
        reusableBagLarge = new Bag("Large Reusable Bag", new BigDecimal("1"), 0.3);

        disposableBagSmall = new Bag("Small Disposable Bag", new BigDecimal("0.25"), 0.1);
        disposableBagMedium = new Bag("Medium Disposable Bag", new BigDecimal("0.50"), 0.2);
        disposableBagLarge = new Bag("Large Disposable Bag", new BigDecimal("1"), 0.3);
	}
	@After
	public void tearDown() {
		bag = null;
		reusableBagLarge = null;
		reusableBagMedium = null;
		reusableBagSmall = null;
		disposableBagLarge = null;
		disposableBagMedium = null;
		disposableBagSmall = null;
		PurchasedItems.reset();
	}
//	tests if the bag was constructed correctly
	@Test
	public void validBagConstructorTest() {
        assertEquals(reusableBagSmall.getDescription(), "Small Reusable Bag");
        assertEquals(reusableBagSmall.getPrice(), new BigDecimal("0.25"));
        assertEquals(reusableBagSmall.getWeight(), 0.1, 0.0001);

		assertEquals(reusableBagMedium.getDescription(), "Medium Reusable Bag");
		assertEquals(reusableBagMedium.getPrice(), new BigDecimal("0.50"));
		assertEquals(reusableBagMedium.getWeight(), 0.2, 0.0001);

		assertEquals(reusableBagLarge.getDescription(), "Large Reusable Bag");
		assertEquals(reusableBagLarge.getPrice(), new BigDecimal("1"));
		assertEquals(reusableBagLarge.getWeight(), 0.3, 0.0001);

		assertEquals(disposableBagSmall.getDescription(), "Small Disposable Bag");
		assertEquals(disposableBagSmall.getPrice(), new BigDecimal("0.25"));
		assertEquals(disposableBagSmall.getWeight(), 0.1, 0.0001);

		assertEquals(disposableBagMedium.getDescription(), "Medium Disposable Bag");
		assertEquals(disposableBagMedium.getPrice(), new BigDecimal("0.50"));
		assertEquals(disposableBagMedium.getWeight(), 0.2, 0.0001);

		assertEquals(disposableBagLarge.getDescription(), "Large Disposable Bag");
		assertEquals(disposableBagLarge.getPrice(), new BigDecimal("1"));
		assertEquals(disposableBagLarge.getWeight(), 0.3, 0.0001);
    }

	@Test
	public void invalidBagConstructorTest() {
		boolean failed = false;
		try {
			bag = new Bag(null, new BigDecimal("0.50"), 5);
		} catch (IllegalArgumentException e) {
			failed = true;
		}
		assertTrue(failed);

		failed = false;
		try {
			bag = new Bag("test bag", null, 5);
		} catch (SimulationException e) {
			failed = true;
		}
		assertTrue(failed);

		failed = false;

		try {
			bag = new Bag("test bag", new BigDecimal("0.50"), -5);
		} catch (IllegalArgumentException e) {
			failed = true;
		}
		assertTrue(failed);
	}


}
