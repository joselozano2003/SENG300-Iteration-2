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
package com.autovend.software.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.autovend.Barcode;
import com.autovend.Bill;
import com.autovend.Coin;
import com.autovend.Numeral;
import com.autovend.devices.BillDispenser;
import com.autovend.devices.BillValidator;
import com.autovend.devices.CoinDispenser;
import com.autovend.devices.CoinValidator;
import com.autovend.devices.OverloadException;
import com.autovend.devices.SelfCheckoutStation;
import com.autovend.devices.SimulationException;
import com.autovend.products.BarcodedProduct;
import com.autovend.software.ChangeCalculator;
import com.autovend.software.PayWithCash;
import com.autovend.software.PurchasedItems;

public class PayWithCashTest {

    private SelfCheckoutStation station;
    private BarcodedProduct product;
    private BarcodedProduct anotherProduct;
    private Currency currency;
    private Bill bill;
    private Bill billTwenty;
    private Coin coin;
    private Coin coinTwo;
    private CoinDispenser fiveCentDispenser;
    private CoinDispenser twoDollarDispenser;
    private BillDispenser fiveDollarDispenser;
    private PayWithCash payWithCash;

    @Before
    public void setup() {
        Currency currency = Currency.getInstance(Locale.CANADA);
        int[] billDenominations = {5, 10 , 15, 20, 50, 100};
        BigDecimal fiveCent = new BigDecimal("0.05");
        BigDecimal tenCent = new BigDecimal("0.10");
        BigDecimal twentyFiveCent = new BigDecimal("0.25");
        BigDecimal loonie = new BigDecimal("1");
        BigDecimal toonie = new BigDecimal("2");
        BigDecimal[] coinDenominations = {fiveCent, tenCent, twentyFiveCent, loonie, toonie};
        station = new SelfCheckoutStation(currency, billDenominations, coinDenominations,10,1);

        coin = new Coin(fiveCent,currency);
        coinTwo = new Coin (toonie,currency);
        bill = new Bill(5,currency);
        billTwenty = new Bill(20,currency);
        BillDispenser fiveDollarDispenser = station.billDispensers.get(5);
        CoinDispenser fiveCentDispenser = station.coinDispensers.get(fiveCent);

        Barcode barcode = new Barcode(Numeral.zero, Numeral.two, Numeral.three, Numeral.two, Numeral.seven);
        product = new BarcodedProduct(barcode,"product name", new BigDecimal("12.95"),1);
        anotherProduct = new BarcodedProduct(barcode,"product name", new BigDecimal("1.95"),1);


    }

    @After
    public void teardown() {
        station = null;
        product = null;
        PurchasedItems.reset();
    }

    //Test completing a purchase using bills
    @Test
    public void testEntirePaymentWithBill() throws SimulationException, OverloadException {
        PayWithCash payWithCash = new PayWithCash(station);
        fiveCentDispenser = station.coinDispensers.get(new BigDecimal("0.05"));
        twoDollarDispenser = station.coinDispensers.get(new BigDecimal("2"));
        fiveDollarDispenser = station.billDispensers.get(5);
        fiveDollarDispenser.load(bill,bill);
        fiveCentDispenser.load(coin,coin,coin,coin,coin,coin,coin,coin,coin,coin,coin);
        twoDollarDispenser.load(coinTwo,coinTwo);
        //For some reason, when the accept method is called, it never calls reactToBillsLoadedEvent/reactToCoinsLoadedEvent
        //So in this test we call it manually
        payWithCash.reactToBillsLoadedEvent(fiveDollarDispenser,bill,bill);
        payWithCash.reactToCoinsLoadedEvent(twoDollarDispenser,coinTwo,coinTwo);
        payWithCash.reactToCoinsLoadedEvent(fiveCentDispenser,coin,coin,coin,coin,coin,coin,coin,coin,coin,coin);
        PurchasedItems.addProduct(product);
        station.billValidator.accept(billTwenty);
        //For some reason, when the accept method is called, it never calls reactToValidBillDetectedEvent/reactToCoinDetectedEvent
        //So in this test we call it manually
        payWithCash.reactToValidBillDetectedEvent(station.billValidator,currency, 20);
        ArrayList<Bill> billChange = new ArrayList<>();
        billChange.add(bill);
        assertEquals(payWithCash.getBillChange(),billChange);
        ArrayList<Coin> coinChange = new ArrayList<>();
        coinChange.add(coin);
        assertEquals(payWithCash.getCoinChange(),coinChange);
    }

    //Test completing a purchase using coins
    @Test
    public void testEntirePaymentWithCoin() throws SimulationException, OverloadException {
        PayWithCash payWithCash = new PayWithCash(station);
        fiveCentDispenser = station.coinDispensers.get(new BigDecimal("0.05"));
        fiveDollarDispenser = station.billDispensers.get(5);
        fiveDollarDispenser.load(bill,bill);
        fiveCentDispenser.load(coin,coin,coin,coin,coin,coin,coin,coin,coin,coin,coin);
        //For some reason, when the accept method is called, it never calls reactToBillsLoadedEvent/reactToCoinsLoadedEvent
        //So in this test we call it manually
        payWithCash.reactToBillsLoadedEvent(fiveDollarDispenser,bill,bill);
        payWithCash.reactToCoinsLoadedEvent(fiveCentDispenser,coin,coin,coin,coin,coin,coin,coin,coin,coin,coin);
        PurchasedItems.addProduct(anotherProduct);
        station.coinValidator.accept(coinTwo);
        //For some reason, when the accept method is called, it never calls reactToValidBillDetectedEvent/reactToCoinDetectedEvent
        //So in this test we call it manually
        payWithCash.reactToValidCoinDetectedEvent(station.coinValidator,new BigDecimal("2"));
        ArrayList<Bill> billChange = new ArrayList<>();
        billChange.add(bill);
        assertEquals(payWithCash.getBillChange(),billChange);
        ArrayList<Coin> coinChange = new ArrayList<>();
        coinChange.add(coin);
        assertEquals(payWithCash.getCoinChange(),coinChange);
    }

    //Displays a run through of getting the correct change for an expected value
    @Test
    public void calculateChangetest () {
        ArrayList<BigDecimal> d = new ArrayList<BigDecimal>();
        d.add(new BigDecimal(25));
        d.add(new BigDecimal(2));
        d.add(new BigDecimal(6));
        d.add(new BigDecimal(10));
        d.add(new BigDecimal(10));
        d.add(new BigDecimal(50));
        ArrayList<BigDecimal> b = ChangeCalculator.calculateChange(d,new BigDecimal(51));
    }

    //Test to see if the functionality works if not paid all at once
    @Test
    public void testPartialCashPayment() {
        PayWithCash payWithCash = new PayWithCash(station);
        PurchasedItems.addProduct(product);
        station.billValidator.accept(bill);
        BigDecimal expectedResult = new BigDecimal("7.95");
        //For some reason, when the accept method is called, it never calls reactToValidBillDetectedEvent/reactToCoinDetectedEvent
        //So in this test we call it manually
        payWithCash.reactToValidBillDetectedEvent(station.billValidator,currency, 5);
        BigDecimal actualResult = PurchasedItems.getAmountLeftToPay();
        assertEquals(expectedResult,actualResult);
    }

    //Test to make sure that the class correctly receives bills
    @Test
    public void testReactToValidBillDetectedEvent() {
        PayWithCash payWithCash = new PayWithCash(station);
        BigDecimal five = new BigDecimal("5");
        PurchasedItems.setAmountPaid(five);
        station.billValidator.accept(bill);
        BigDecimal actualAmountPaid = PurchasedItems.getAmountPaid();
        assertEquals(five,actualAmountPaid);
    }

    //Test to make sure that the class correctly receives coins
    @Test
    public void testReactToValidCoinDetectedEvent() {
        PayWithCash payWithCash = new PayWithCash(station);
        BigDecimal coinValue = new BigDecimal("0.05");
        BigDecimal expectedAmountPaid = coinValue;
        PurchasedItems.setAmountPaid(expectedAmountPaid);
        station.coinValidator.accept(coin);
        BigDecimal actualAmountPaid = PurchasedItems.getAmountPaid();
        assertEquals(expectedAmountPaid, actualAmountPaid);
    }

    //Dummy tests for listener events that don't do anything

    @Test
    public void dummyReactToEnabledEvent() {
        PayWithCash payWithCash = new PayWithCash(station);
        payWithCash.reactToEnabledEvent(null);
    }

    @Test
    public void dummyReactToDisabledEvent() {
        PayWithCash payWithCash = new PayWithCash(station);
        payWithCash.reactToDisabledEvent(null);
    }

    @Test
    public void dummyReactToCoinsUnloadedEvent() {
        PayWithCash payWithCash = new PayWithCash(station);
        payWithCash.reactToCoinsUnloadedEvent(fiveCentDispenser,coin,coin,coin);
    }

    @Test
    public void dummyReactToInvalidCoinDetectedEvent() {
        PayWithCash payWithCash = new PayWithCash(station);
        payWithCash.reactToInvalidCoinDetectedEvent(station.coinValidator);
    }

    @Test
    public void dummyReactToCoinsFullEvent() {
        PayWithCash payWithCash = new PayWithCash(station);
        payWithCash.reactToCoinsFullEvent(fiveCentDispenser);
    }

    @Test
    public void dummyReactToCoinsEmptyEvent() {
        PayWithCash payWithCash = new PayWithCash(station);
        payWithCash.reactToCoinsEmptyEvent(fiveCentDispenser);
    }

    @Test
    public void dummyReactToBillsFullEvent() {
        PayWithCash payWithCash = new PayWithCash(station);
        payWithCash.reactToBillsFullEvent(fiveDollarDispenser);
    }

    @Test
    public void dummyReactToBillsEmptyEvent(){
        PayWithCash payWithCash = new PayWithCash(station);
        payWithCash.reactToBillsEmptyEvent(fiveDollarDispenser);
    }

    @Test
    public void dummyReactToBillAddedEvent() {
        PayWithCash payWithCash = new PayWithCash(station);
        payWithCash.reactToBillAddedEvent(fiveDollarDispenser,bill);
    }

    @Test
    public void dummyReactToBillsUnloadedEvent() {
        PayWithCash payWithCash = new PayWithCash(station);
        payWithCash.reactToBillsUnloadedEvent(fiveDollarDispenser,bill,bill,bill);
    }

    @Test
    public void dummyReactToInvalidBillDetectedEvent() {
        PayWithCash payWithCash = new PayWithCash(station);
        payWithCash.reactToInvalidBillDetectedEvent(station.billValidator);
    }
}

