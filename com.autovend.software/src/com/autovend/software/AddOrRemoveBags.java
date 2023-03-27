package com.autovend.software;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.autovend.Barcode;
import com.autovend.devices.*;
import com.autovend.products.*;

public class AddOrRemoveBags {
	public ArrayList<Bag> bags;
	public int ownbags;
	public int storebags;
	public int bagsremaining;
	public BigDecimal price = new BigDecimal("0.25");
	public BigDecimal weight = new BigDecimal("0.050");
	public PurchasedItems purchase;
	public AddOrRemoveBags() {
		
	}
	
	public void purchasebag(Barcode code, String description, BigDecimal price, double expectedWeight) throws SimulationException {
		if (bagsremaining == 0) {
			throw new SimulationException("We are out of bags. Get some more from the attendant bub.");
		}
		else {
			bagsremaining--;
			Bag newbag = new Bag(code, description, price, expectedWeight, true);
			bags.add(newbag);
			purchase.addProduct(newbag);
			if (storebags == Integer.MAX_VALUE) {
				throw new SimulationException("what - how did you even buy that many bags...?");
			}
			storebags++;
		}
	}
	public void addownbag() {
		if (ownbags == Integer.MAX_VALUE) {
			throw new SimulationException("...why do you even have this many bags in the first place?");
		}
	}
	public void removebag(int bagnumber) throws SimulationException {
		try {
			Bag tempbag = bags.get(bagnumber);
			if (tempbag.gettype() == true) {
				storebags--;
				if (tempbag.baggage.isEmpty()) {
					bags.remove(bagnumber);
				}
				else {
					throw new SimulationException("Empty this bag first. You think these are free?");
				}
			}
			else {
				ownbags--;
				if (tempbag.baggage.isEmpty()) {
					bags.remove(bagnumber);
				}
				else {
					throw new SimulationException("Empty this bag first. Or do you plan on returning what is in this bag?");
				}
			}
		}
		catch (Exception e) {
			throw new SimulationException("What are you even trying to remove? Air?");
		}
	}
	public void addtobag(Bag bag) {
		
	}
	public void removefrombag(Bag bag) {
		
	}
	public void monitorbags() {
		
	}
	public void emptybag(Bag bag) {
		
	}
}
