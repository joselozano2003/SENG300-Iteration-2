/**
 * Members for Iteration 2:
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

/*
 * Testing for PayWithCredit and PayWithDebit, specifically, when paying with tap, insert and swipe
 * the test can all pass, but because of the hardwares randomize fail feature, such as magnetic strip
 * not working the test will fail, but all test are able to pass. if running the tests for the first time
 * and all of them do not pass, please keep running them until all of them pass, this mainly due to the given 
 * hardwares randomness as mentioned above. 
 * 
 * The testing for insufficient balance or credit the asserts are to assure that nothing changes and the card provider 
 * should negate the request
 */

package com.autovend.software.test;
import static org.junit.Assert.*;


import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Currency;
import java.util.GregorianCalendar;

import com.autovend.*;
import com.autovend.external.ProductDatabases;
import com.autovend.software.ScanItems;
import org.junit.*;
import org.junit.Before;
import org.junit.Test;

import com.autovend.devices.SelfCheckoutStation;
import com.autovend.devices.SimulationException;
import com.autovend.external.CardIssuer;
import com.autovend.products.BarcodedProduct;
import com.autovend.software.PayWithCard;
import com.autovend.software.PurchasedItems;

public class PartiallyPaidTest {
	
	private SelfCheckoutStation scs;
	BigDecimal milkPrice = new BigDecimal(2.50);
	BigDecimal amountToPay;
	BigDecimal companyIssue;
    Numeral[] nMilk = {Numeral.one, Numeral.two, Numeral.three, Numeral.four};
    Barcode barcodeMilk = new Barcode(nMilk);
	BarcodedProduct milk = new BarcodedProduct(barcodeMilk,"milk description",milkPrice,2.00);
	
	
	Calendar exipery = new GregorianCalendar(2025, Calendar.OCTOBER, 31);
	CardIssuer company = new CardIssuer("TD");
	CardIssuer company2 = new CardIssuer("BMO");

	Currency currency = Currency.getInstance("CAD");
	int[] billDenominations = {1, 2, 5, 10};
	BigDecimal[] coinDenominations = {BigDecimal.TEN};
	private SelfCheckoutStation selfCheckoutStation;
	private BigDecimal price1, price2, price3;
	private Double weight1, weight2, weight3;
	private String description1, description2, description3;
	private BarcodedProduct itemProduct1, itemProduct2, itemProduct3;
	private BarcodedUnit unitItem1, unitItem2, unitItem3;
	private BigDecimal expectedCartPrice;
	private int maxScaleWeight, sensitivity;
	private double expectedBaggingWeight;
	private ScanItems scanItems;
	private PurchasedItems itemsPurchased;
	private boolean scanFailed1, scanFailed2, scanFailed3;

	// initializing some barcodes to use during tests
	Numeral[] n = {Numeral.one, Numeral.two, Numeral.three};
	Numeral[] m = {Numeral.two, Numeral.three, Numeral.one};
	Numeral[] k = {Numeral.three, Numeral.two, Numeral.one};
	Barcode b1 = new Barcode(n);
	Barcode b2 = new Barcode(m);
	Barcode b3 = new Barcode(k);
	
@Before
public void setUp() {
	
	Currency currency = Currency.getInstance("CAD");
	int[] billDom = {5,10,20};
	BigDecimal[] coinDom = {BigDecimal.valueOf(0.05), BigDecimal.valueOf(0.10),BigDecimal.valueOf(0.25)};
	scs = new SelfCheckoutStation(currency, billDom, coinDom, 10000,2);


	company.addCardData("1234567890123458","debit",exipery,"123",BigDecimal.valueOf(100));

	expectedCartPrice = new BigDecimal(0);
	expectedBaggingWeight = 0.0;

	// initialize a few prices
	price1 = new BigDecimal(2.00);
	price2 = new BigDecimal(3.00);
	price3 = new BigDecimal(4.50);

	//initialize a few weights
	weight1 = 2.0;
	weight2 = 3.5;
	weight3 = 12.0;

	// initialize a few descriptions
	description1 = "item1";
	description2 = "item2";
	description3 = "item3";

	scanFailed1 = false;
	scanFailed2 = false;
	scanFailed3 = false;

	//initialize some products
	itemProduct1 = new BarcodedProduct(b1, description1, price1, weight1);
	itemProduct2 = new BarcodedProduct(b2, description2, price2, weight2);
	itemProduct3 = new BarcodedProduct(b3, description3, price3, weight3);

	// initialize some units associated with the barcodes
	unitItem1 = new BarcodedUnit(b1, weight1);
	unitItem2 = new BarcodedUnit(b2, weight2);
	unitItem3 = new BarcodedUnit(b3, weight3);

	// add the products to the database to access
	ProductDatabases.BARCODED_PRODUCT_DATABASE.put(b1, itemProduct1);
	ProductDatabases.BARCODED_PRODUCT_DATABASE.put(b2, itemProduct2);
	ProductDatabases.BARCODED_PRODUCT_DATABASE.put(b3, itemProduct3);

	maxScaleWeight = 10;
	sensitivity = 1;

	// initialize purchased items constructor
	itemsPurchased = new PurchasedItems();

	// initialize constructor and add each product to the list of products being scanned
	scanItems = new ScanItems(scs);



	//register the observers and enable card readers
	// TODO: register the observers and enable card readers



	//register the observer and enable scanners
	scs.mainScanner.register(scanItems);
	scs.mainScanner.enable();
	scs.handheldScanner.enable();
	scs.handheldScanner.register(scanItems);
	scs.baggingArea.register(scanItems);

}

@Test
public void testPartiallyWithCard() {
	
}

	
}
