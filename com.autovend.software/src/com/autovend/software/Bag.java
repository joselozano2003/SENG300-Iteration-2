package com.autovend.software;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.autovend.Barcode;
import com.autovend.products.BarcodedProduct;

public class Bag extends BarcodedProduct {
	private boolean purchased;
	public ArrayList<PurchasedItems> baggage;
	private BigDecimal capacity;
	public Bag(Barcode barcode, String description, BigDecimal price, double expectedWeight, boolean storebag) {
		super(barcode, description, price, expectedWeight);
		purchased = storebag;
	}
	public boolean gettype() {
		return purchased;
	}
}
