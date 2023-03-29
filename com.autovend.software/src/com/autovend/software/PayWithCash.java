package com.autovend.software;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
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
	private List<Integer> billValues;
	private List<BigDecimal> coinValues;
	private Map<Integer, Integer> billAmount = new HashMap<Integer, Integer>();
	private Map<BigDecimal, Integer> coinAmount = new HashMap<BigDecimal, Integer>();

	// If there is a change, sum up all bills+coins and send to dp
	// paid == due -> Disable/sth


    public PayWithCash(SelfCheckoutStation station) {
        super(station);
		PurchasedItems items = new PurchasedItems();
		/*
        if (amountToPay.compareTo(super.getAmountDue()) > 0) {
			this.amountToPay = super.getAmountDue();
		} else this.amountToPay = amountToPay;
        this.billDispenser = billDispenser;
        this.coinDispenser = coinDispenser;*/
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

    	// station.billValidator.accept(bill);
    	
    }

    @Override
    public void reactToBillRemovedEvent(BillDispenser dispenser, Bill bill) {
    	
    	// station.billOutput.removeDanglingBill();

    }

    @Override
    public void reactToBillsLoadedEvent(BillDispenser dispenser, Bill... bills) {

    }

    @Override
    public void reactToBillsUnloadedEvent(BillDispenser dispenser, Bill... bills) {

    }

    @Override
    public void reactToValidBillDetectedEvent(BillValidator validator, Currency currency, int value) {
    	
    	amountPaid = amountPaid.add(BigDecimal.valueOf(value));

    	if (amountPaid.compareTo(amountDue) > 0) {
    		for (int i : station.billDenominations) {
    			this.billValues.add(i);
    			this.billAmount.put(i, station.billDispensers.get(i).size());
    		}

    		for (BigDecimal i : station.coinDenominations) {
    			this.coinValues.add(i);
    			this.coinAmount.put(i, station.coinDispensers.get(i).size());
    		}
    	}

    	// Then pass to dp by Vic to see if we have enough change and emit

    }

    @Override
    public void reactToInvalidBillDetectedEvent(BillValidator validator) {

    }


	@Override
	public void reactToValidCoinDetectedEvent(CoinValidator validator, BigDecimal value) {
		// TODO Auto-generated method stub
		amountPaid = amountPaid.add(value);

		if (amountPaid.compareTo(amountDue) > 0) {
    		for (int i : station.billDenominations) {
    			this.billValues.add(i);
    			this.billAmount.put(i, station.billDispensers.get(i).size());
    		}

    		for (BigDecimal i : station.coinDenominations) {
    			this.coinValues.add(i);
    			this.coinAmount.put(i, station.coinDispensers.get(i).size());
    		}
    	}


		// Then pass to dp by Vic to see if we have enough change and emit


//		// While the amount to pay is >0, in other words, it is needed to provide changes:
//    	while (amountPaid.compareTo(amountDue) > 0) {
//    		// I want to loop from the largest one but don't know how to sort the hashmap tbh
//    		for (Entry<BigDecimal, CoinDispenser> entry : this.station.coinDispensers.entrySet()) {
//    			//coin = new Coin(entry.getKey(), "CAD");
//    			coin = new Coin(entry.getKey(), tempcurrency);
//    			try {
//					entry.getValue().emit();
//					// changes sth to amountDue/amountPaid to avoid infinite loop
//					// amountDue = amountDue.subtract(BigDecimal.valueOf(entry.getKey()));
//				} catch (DisabledException | EmptyException | OverloadException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//    		}
//    	}

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
