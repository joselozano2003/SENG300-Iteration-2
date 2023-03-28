package com.autovend.software;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.autovend.Bill;
import com.autovend.Coin;
import com.autovend.devices.AbstractDevice;
import com.autovend.devices.BillDispenser;
import com.autovend.devices.BillSlot;
import com.autovend.devices.BillValidator;
import com.autovend.devices.CoinDispenser;
import com.autovend.devices.CoinValidator;
import com.autovend.devices.DisabledException;
import com.autovend.devices.EmptyException;
import com.autovend.devices.OverloadException;
import com.autovend.devices.SelfCheckoutStation;
import com.autovend.devices.observers.AbstractDeviceObserver;
import com.autovend.devices.observers.BillDispenserObserver;
import com.autovend.devices.observers.BillValidatorObserver;
import com.autovend.devices.observers.CoinDispenserObserver;
import com.autovend.devices.observers.CoinValidatorObserver;

/**
 * Class that allows customer to pay with cash.
 *
 *
 */
public class PayWithCash extends Pay implements BillDispenserObserver, BillValidatorObserver, CoinDispenserObserver, CoinValidatorObserver {

	private BigDecimal amountToPay;
	private BillDispenser billDispenser;
	private CoinDispenser coinDispenser;
	private Bill bill;
	private Coin coin;
	private Currency tempcurrency = Currency.getInstance("CAD");

	
    public PayWithCash(SelfCheckoutStation station, PurchasedItems items) {
        super(station, items);
        if (amountToPay.compareTo(super.getAmountDue()) > 0) {
			this.amountToPay = super.getAmountDue();
		} else this.amountToPay = amountToPay;
        this.billDispenser = billDispenser;
        this.coinDispenser = coinDispenser;
    }

    @Override
    public void reactToEnabledEvent(AbstractDevice<? extends AbstractDeviceObserver> device) {

    }

    @Override
    public void reactToDisabledEvent(AbstractDevice<? extends AbstractDeviceObserver> device) {

    }

    @Override
    public void reactToBillsFullEvent(BillDispenser dispenser) {

    }

    @Override
    public void reactToBillsEmptyEvent(BillDispenser dispenser) {

    }

    @Override
    public void reactToBillAddedEvent(BillDispenser dispenser, Bill bill) {

    	station.billValidator.accept(bill);
    	
    }

    @Override
    public void reactToBillRemovedEvent(BillDispenser dispenser, Bill bill) {
    	
    	station.billOutput.removeDanglingBill();

    }

    @Override
    public void reactToBillsLoadedEvent(BillDispenser dispenser, Bill... bills) {

    }

    @Override
    public void reactToBillsUnloadedEvent(BillDispenser dispenser, Bill... bills) {

    }

    @Override
    public void reactToValidBillDetectedEvent(BillValidator validator, Currency currency, int value) {
    	
    	amountToPay = amountToPay.subtract(BigDecimal.valueOf(value));
    	// While the amount to pay is >0, in other words, it is needed to provide changes:
    	while (amountToPay.compareTo(new BigDecimal(0.00)) < -5) {
    		// I want to loop from the largest one but don't know how to sort the hashmap tbh
    		for (Map.Entry<Integer, BillDispenser> entry : this.station.billDispensers.entrySet()) {
    			bill = new Bill(entry.getKey(), currency);
    			try {
					entry.getValue().emit();
				} catch (DisabledException | EmptyException | OverloadException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
    	// If the changes left is smaller than 5, then use coins
    	if (amountToPay.compareTo(new BigDecimal(0.00)) < 0 && amountToPay.compareTo(new BigDecimal(0.00)) > -5) {
    		CoinValidator tempValidator = null;
    		reactToValidCoinDetectedEvent(tempValidator, new BigDecimal(0.00));
    	}

    }

    @Override
    public void reactToInvalidBillDetectedEvent(BillValidator validator) {

    }

    void pay() {

    }

	@Override
	public void reactToValidCoinDetectedEvent(CoinValidator validator, BigDecimal value) {
		// TODO Auto-generated method stub
		amountToPay = amountToPay.subtract(value);
		// While the amount to pay is >0, in other words, it is needed to provide changes:
    	while (amountToPay.compareTo(new BigDecimal(0.00)) < 0) {
    		// I want to loop from the largest one but don't know how to sort the hashmap tbh
    		for (Entry<BigDecimal, CoinDispenser> entry : this.station.coinDispensers.entrySet()) {
    			//coin = new Coin(entry.getKey(), "CAD");
    			coin = new Coin(entry.getKey(), tempcurrency);
    			try {
					entry.getValue().emit();
				} catch (DisabledException | EmptyException | OverloadException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
    	
	}

	@Override
	public void reactToInvalidCoinDetectedEvent(CoinValidator validator) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reactToCoinsFullEvent(CoinDispenser dispenser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reactToCoinsEmptyEvent(CoinDispenser dispenser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reactToCoinAddedEvent(CoinDispenser dispenser, Coin coin) {
		// TODO Auto-generated method stub
		
		station.coinValidator.accept(coin);
		
	}

	@Override
	public void reactToCoinRemovedEvent(CoinDispenser dispenser, Coin coin) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reactToCoinsLoadedEvent(CoinDispenser dispenser, Coin... coins) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reactToCoinsUnloadedEvent(CoinDispenser dispenser, Coin... coins) {
		// TODO Auto-generated method stub
		
	}


}
