package com.autovend.software.test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Currency;
import java.util.GregorianCalendar;

import org.junit.*;
import org.junit.Before;
import org.junit.Test;

import com.autovend.Barcode;
import com.autovend.Card;
import com.autovend.CreditCard;
import com.autovend.DebitCard;
import com.autovend.Numeral;
import com.autovend.devices.SelfCheckoutStation;
import com.autovend.devices.SimulationException;
import com.autovend.external.CardIssuer;
//import com.autovend.external.CardIssuer.CardRecord;
import com.autovend.products.BarcodedProduct;
import com.autovend.software.Pay;
import com.autovend.software.PayWithCard;
import com.autovend.software.PurchasedItems;

public class PayWithCardTest{
	
	
	
	


	private SelfCheckoutStation scs;
	private PurchasedItems items;
	private BigDecimal amountToPay;
	private CardIssuer companyIssue;
	BigDecimal milkPrice = new BigDecimal(2.50);
    Numeral[] nMilk = {Numeral.one, Numeral.two, Numeral.three, Numeral.four};
    Barcode barcodeMilk = new Barcode(nMilk);
	BarcodedProduct milk = new BarcodedProduct(barcodeMilk,"milk description",milkPrice,2.00);
	
	
	Calendar exipery = new GregorianCalendar(2025, Calendar.OCTOBER, 31);
	CardIssuer company = new CardIssuer("TD");
	PurchasedItems totalCost = new PurchasedItems();
	
	
@Before
public void setUp() {
	
	Currency currency = Currency.getInstance("CAD");
	int[] billDom = {5,10,20};
	BigDecimal[] coinDom = {BigDecimal.valueOf(0.05), BigDecimal.valueOf(0.10),BigDecimal.valueOf(0.25)};
	scs = new SelfCheckoutStation(currency, billDom, coinDom, 10000,2);
	

	totalCost.addProduct(milk);
	CreditCard Credit = new CreditCard("CREDIT","1234567890123456", "credit", "123", "1234", true, true);
	DebitCard Debit = new DebitCard("DEBIT","1234567890123456", "debit", "123", "1234", true, true);
	PayWithCard PayWithDebit = new PayWithCard(scs,company);
	PayWithCard PayWithCredit = new PayWithCard(scs,company);
	company.addCardData("1234567890123457","credit",exipery,"123",BigDecimal.valueOf(100));
	company.addCardData("1234567890123458","debit",exipery,"123",BigDecimal.valueOf(100));
 

}
	
@After
public void tearDown() {
	scs = null;
	company = null;
	items = null;
	amountToPay = null;
	companyIssue = null;
	exipery = null;
}


@Test
public void testDebitTap() throws IOException {
	DebitCard Debit = new DebitCard("DEBIT","1234567890123456", "debit", "123", "1234", true, true);
	PayWithCard PayWithDebit = new PayWithCard(scs,company);
	//System.out.println(totalCost.getTotalPrice());
	
	totalCost.getTotalPrice();
	PayWithDebit.pay(milkPrice);
	try {
		scs.cardReader.tap(Debit);
		PayWithDebit.pay(milkPrice);
		System.out.println(totalCost.getTotalPrice());
		System.out.println(totalCost.getAmountLeftToPay());
		System.out.println(totalCost.getAmountPaid());
		
//		PayWithDebit.pay(milkPrice);
	} catch(SimulationException e)
    {
        e.printStackTrace();
    }catch (IOException e) {
		
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//System.out.println(totalCost.getTotalPrice());
	Assert.assertEquals(new BigDecimal(2.50), totalCost.getAmountPaid());
}









}
