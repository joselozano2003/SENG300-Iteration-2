package com.autovend.software;

import com.autovend.devices.SelfCheckoutStation;
import com.autovend.software.PurchasedItems;

public abstract class AddItem {
	
	protected SelfCheckoutStation selfCheckOut;
	
	protected AddItem(SelfCheckoutStation scs) {
		this.selfCheckOut = scs;
	}
	
	protected void additem(Product product, double weightOfItem) {
		if(product instanceof BarcodedProduct)
        {
            PurchasedItems.addBarcodedProduct(product);
        }
		// dont need for now
        else if(product instanceof PLUCodedProduct)
        {

        }
        else
        {
            PurchasedItems.addProduct(product, weightOfItem);
        }
        selfCheckOut.baggingArea.add(new SellableUnit(weightOfItem));
	}
    protected void removeitem(Product product, double weightOfItem)
    {
        if(product instanceof BarcodedProduct)
        {
            PurchasedItems.removeBarcodedProduct(product);
        }
        else if(product instanceof PLUCodedProduct)
        {

        }
        else
        {
            PurchasedItems.removeProduct(product, weightOfItem);
        }
        selfCheckOut.baggingArea.remove(new SellableUnit(weightOfItem));
    }
}
