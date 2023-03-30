package com.autovend.software;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.autovend.Barcode;
import com.autovend.devices.OverloadException;
import com.autovend.devices.SimulationException;
import com.autovend.products.BarcodedProduct;

public class Bag extends BarcodedProduct {
	private boolean purchased;
	public ArrayList<PurchasedItems> baggage;
	private double maxweight = 1000; // it is literally to simulate too many items in a bag
	private double bagweight;
	public Bag(Barcode barcode, String description, BigDecimal price, double expectedWeight, boolean storebag) {
		super(barcode, description, price, expectedWeight);
		purchased = storebag;
		baggage = new ArrayList<PurchasedItems>();
	}
	public boolean gettype() {
		return purchased;
	}
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
	}
}
