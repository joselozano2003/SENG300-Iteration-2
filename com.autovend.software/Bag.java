package com.autovend.software;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.autovend.Barcode;
import com.autovend.devices.OverloadException;
import com.autovend.devices.SimulationException;
import com.autovend.products.BarcodedProduct;

public class Bag extends Product{
	private double bagweight;
    public static BigDecimal storeBagPrice = new BigDecimal("0.25");
	public static final double storeBagWeight = 0.05;
	public Bag() {
		super(storeBagPrice,true);
        bagweight = storeBagWeight;
	}
    public Bag(double ownBagWeight)
    {
        super(new BigDecimal(0), true);
        bagweight = ownBagWeight;
    }
    public double getWeight()
    {
        return bagweight;
    }
    /* 
	public void additem(PurchasedItems item) throws OverloadException {
		
		if ((bagweight + item.getTotalExpectedWeight())> maxweight) {
			throw new SimulationException("That is too many items. Learn to use multiple bags.");
		}
		baggage.add(item);
		bagweight += item.getTotalExpectedWeight();
	}
	public void removeitem(PurchasedItems item) throws SimulationException {
		if (baggage.isEmpty()) {
			throw new SimulationException("What are you even trying to remove? Air?");
		}
		else {
			baggage.remove(item);
			bagweight -= item.getTotalExpectedWeight();
		}
	}
	public void emptyBag() {
		if (baggage.isEmpty()) {
			throw new SimulationException("What are you even trying to remove? Air?");
		}
		else {
			for (int a=0;a<baggage.size();a++) {
				baggage.remove(a);
			}
		}
	}*/
}
