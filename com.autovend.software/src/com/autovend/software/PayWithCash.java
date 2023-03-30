package com.autovend.software;

import java.math.BigDecimal;
import java.util.ArrayList;
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
    }
    
    public void PassChange(BigDecimal c) {
    	ArrayList<BigDecimal> d = new ArrayList<BigDecimal>();
    	
    	// Pass all bills in dispensers into arraylist
    	for (int i : station.billDenominations) {
    		if (i == 100) {
    			d.add(new BigDecimal(100.00));
    		}
    		else if (i == 50) {
    			d.add(new BigDecimal(50.00));
    		}
    		else if (i == 20) {
    			d.add(new BigDecimal(20.00));
    		}
    		else if (i == 10) {
    			d.add(new BigDecimal(10.00));
    		}
    		else if (i == 5) {
    			d.add(new BigDecimal(5.00));
    		}
    	}
    	
    	// Pass all coins in dispensers into arraylist
    	for (BigDecimal i : station.coinDenominations) {
    		if (i == BigDecimal.valueOf(2.00)) {
    			d.add(new BigDecimal(2.00));
    		}
    		else if (i == BigDecimal.valueOf(1.00)) {
    			d.add(new BigDecimal(1.00));
    		}
    		else if (i == BigDecimal.valueOf(0.25)) {
    			d.add(new BigDecimal(0.25));
    		}
    		else if (i == BigDecimal.valueOf(0.10)) {
    			d.add(new BigDecimal(0.10));
    		}
    		else if (i == BigDecimal.valueOf(0.05)) {
    			d.add(new BigDecimal(0.05));
    		}
    	}
    	
		ArrayList<BigDecimal> b = ChangeCalculator.calculateChange(d, c);
		//then emit
		EmitChange(b);
    }

    public void EmitChange(ArrayList<BigDecimal> b) {
    	for (BigDecimal i : b) {

    		for (Map.Entry<Integer, BillDispenser> entry : this.station.billDispensers.entrySet()) {
    			if (BigDecimal.valueOf(entry.getKey()) ==  i) {
    				bill = new Bill(entry.getKey(), null);
    				try {
						entry.getValue().emit();
					} catch (DisabledException | EmptyException | OverloadException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    			}
    		}
    		
    		for (Entry<BigDecimal, CoinDispenser> entry : this.station.coinDispensers.entrySet()) {
    			if (entry.getKey() ==  i) {
    				coin = new Coin(entry.getKey(), null);
    				try {
						entry.getValue().emit();
    				} catch (DisabledException | EmptyException | OverloadException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    			}
    		}
    	}
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
    	
		PurchasedItems.addAmountPaid(new BigDecimal(value));

		// If the AmountLeftToPay < 0 => There is a change need to be returned => call PassChange
    	if (PurchasedItems.getAmountLeftToPay().compareTo(new BigDecimal(0)) < 0) {
    		BigDecimal change = PurchasedItems.getAmountLeftToPay().abs();
    		PassChange(change);
    	}
    	
    }

    @Override
    public void reactToInvalidBillDetectedEvent(BillValidator validator) {

    }


	@Override
	public void reactToValidCoinDetectedEvent(CoinValidator validator, BigDecimal value) {
		// TODO Auto-generated method stub
		PurchasedItems.addAmountPaid(value);

		// If the AmountLeftToPay < 0 => There is a change need to be returned => call PassChange
    	if (PurchasedItems.getAmountLeftToPay().compareTo(new BigDecimal(0)) < 0) {
    		BigDecimal change = PurchasedItems.getAmountLeftToPay().abs();
    		PassChange(change);
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
