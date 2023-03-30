package com.autovend.software;

import java.math.*;
import java.util.*;

import com.autovend.*;
import com.autovend.devices.*;
public class AddOrRemoveBags extends AddItem{

	private int bagsremaining;
	public AddOrRemoveBags(SelfCheckoutStation check) {
		super(check);
		bagsremaining = 100;
	}
	public void purchaseBag() throws SimulationException {
		if (bagsremaining == 0) {
			throw new SimulationException("We are out of bags. Get some more from the attendant bub.");
		}
		else {
			additem(new Bag(), Bag.storeBagWeight);
			bagsremaining--;
		}
	}
	public void addOwnBag(double bagweight) {
		additem(new Bag(bagweight), bagweight);
	}
	public void removeBag(Bag bag){
        if(bag == null)
        {
            return;
        }
		removeitem(bag, bag.getWeight());	
	}
	//method to add more store bags
	public void addStoreBag(int numOfBags) {
		if(numOfBags <= 0) {
			throw new SimulationException("Add Bags! Don't Remove them!!");
		}
		bagsremaining+= numOfBags;
	}
    public int getBagsRemaining()
    {
        return bagsremaining;
    }
}