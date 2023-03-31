/** Members for Iteration 2:
 * Ethan Oke (30142180)
 * Jose Camilo Lozano Cetina (30144736)
 * Quinn Leonard (30145315)
 * Efren Garcia (30146181)
 * Nam Anh Vu (30127597)
 * Tyler Nguyen (30158563)
 * Victor Han (30112492)
 * Francisco Huayhualla (30091238)
 * Md Minhazur Rahman Hamim (30145446)
 * Imran Haji (30141571)
 * Sara Dhuka (30124117)
 * Robert (William) Engel (30119608)
 */
package com.autovend.software;

import com.autovend.devices.SelfCheckoutStation;
import com.autovend.products.BarcodedProduct;
import com.autovend.software.observers.AddItemObserver;
import com.autovend.software.PurchasedItems;

public abstract class AddItem {
	
	SelfCheckoutStation check;
	
	public AddItem(SelfCheckoutStation scs) {
		this.check = scs;
	}

	protected void addBag(Bag bag) {
		PurchasedItems.addBag(bag);
	}

	protected void addBarcodedProduct(BarcodedProduct unit) {
		PurchasedItems.addProduct(unit);
	}
}
