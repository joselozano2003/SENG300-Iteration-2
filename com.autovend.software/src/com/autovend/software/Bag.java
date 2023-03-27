package com.autovend.software;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.autovend.Barcode;
import com.autovend.products.BarcodedProduct;

public class Bag extends BarcodedProduct {
	public Bag(Barcode barcode, String description, BigDecimal price, double expectedWeight) {
		super(barcode, description, price, expectedWeight);
		
	}

	public ArrayList<PurchasedItems> baggage;
	private BigDecimal capacity;

}
