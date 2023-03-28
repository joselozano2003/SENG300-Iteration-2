package com.autovend.software;

import java.math.*;
import java.util.*;

import com.autovend.*;
import com.autovend.devices.*;

public class AddOrRemoveBags {
	public ArrayList<Bag> bags;
	public int ownbags;
	public int storebags;
	public int bagsremaining;
	public BigDecimal price = new BigDecimal("0.25");
	public double weight = 0.05;
	public PurchasedItems purchase;
	public AddOrRemoveBags() {}
	public void purchasebag(Barcode code, String description) throws SimulationException {
		if (bagsremaining == 0) {
			throw new SimulationException("We are out of bags. Get some more from the attendant bub.");
		}
		else {
			bagsremaining--;
			Bag newbag = new Bag(code, description, price, weight, true);
			bags.add(newbag);
			purchase.addProduct(newbag);
			if (storebags == Integer.MAX_VALUE) {
				throw new SimulationException("what - how did you even buy that many bags...?");
			}
			storebags++;
		}
	}
	public void addownbag(Barcode code, String description, BigDecimal noprice) {
		Bag selfbag = new Bag(code, description, noprice, weight, false);
		bags.add(selfbag);
		if (ownbags == Integer.MAX_VALUE) {
			throw new SimulationException("...why do you even have this many bags in the first place?");
		}
		
	}
	public void removebag(Bag tempbag) throws SimulationException {
		try {
			if (tempbag.gettype() == true) {
				storebags--;
				if (tempbag.baggage.isEmpty()) {
					bags.remove(tempbag);
				}
				else {
					throw new SimulationException("Empty this bag first. You think these are free?");
				}
			}
			else {
				ownbags--;
				if (tempbag.baggage.isEmpty()) {
					bags.remove(tempbag);
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
	public void monitorbags() {
		for (int i = 0; i<bags.size();i++) {
			Bag bag = bags.get(i);
			System.out.println("Bag " + bag.getDescription() + ":");
			System.out.println("Purchasable?: " + bag.gettype());
			System.out.println("Contents:");
			for (int a = 0; a <bag.baggage.size();a++) {
				System.out.println(bag.baggage.get(a));
			}
		}
	}
}
