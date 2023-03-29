package com.autovend.software;

import java.math.*;
import java.util.*;

import com.autovend.*;
import com.autovend.devices.*;

public class AddOrRemoveBags {
	public ArrayList<Bag> bags;
	public int ownbags;
	public int storebags;
	public ArrayList<Bag> bagsremaining;
	public BigDecimal price = new BigDecimal("0.25");
	public double weight = 0.05;
	public PurchasedItems purchase;
	public AddOrRemoveBags() {
		bags = new ArrayList<Bag>();
		ownbags = 0;
		storebags = 0;
		bagsremaining = new ArrayList<Bag>();
		purchase = new PurchasedItems();
	}
	public void purchaseBag() throws SimulationException {
		if (bagsremaining.isEmpty()) {
			throw new SimulationException("We are out of bags. Get some more from the attendant bub.");
		}
		else {
			bags.add(bagsremaining.get(0));
			purchase.addProduct(bagsremaining.get(0));
			bagsremaining.remove(0);
			if (storebags == Integer.MAX_VALUE) {
				throw new SimulationException("what - how did you even buy that many bags...?");
			}
			storebags++;
		}
	}
	public void addOwnBag(String description, double bagweight) {
		Barcode zerocode = new Barcode(Numeral.valueOf("zero"));
		Bag selfbag = new Bag(zerocode, description, BigDecimal.valueOf(0.00000000000001), bagweight, false);
		bags.add(selfbag);
		if (ownbags == Integer.MAX_VALUE) {
			throw new SimulationException("...why do you even have this many bags in the first place?");
		}
		ownbags++;
	}
	public void removeBag(Bag tempbag) throws SimulationException {
		try {
			if (tempbag.gettype() == true) {
				if (tempbag.baggage.isEmpty()) {
					bags.remove(tempbag);
					storebags--;
				}
				else {
					throw new SimulationException("Empty this bag first. You think these are free?");
				}
			}
			else {
				if (tempbag.baggage.isEmpty()) {
					bags.remove(tempbag);
					ownbags--;
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
	public void monitorBags() {
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
	//method to add more store bags
	public void addStoreBag(Bag bag) {
		if(bag == null) {
			throw new SimulationException("Can't add a null bag");
		}
		bagsremaining.add(bag);
	}
}
