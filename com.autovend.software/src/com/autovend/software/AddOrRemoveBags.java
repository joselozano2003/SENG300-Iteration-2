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

import java.math.*;
import java.util.ArrayList;

import com.autovend.devices.*;

// extend the Parent class
public class AddOrRemoveBags extends AddItem{
    ArrayList<Bag> listOfStoreBags;

    public AddOrRemoveBags(SelfCheckoutStation scs) {
        super(scs);

        listOfStoreBags = new ArrayList<Bag>();

        Bag reusableBagSmall = new Bag("Small Reusable Bag", new BigDecimal("0.25"), 0.1);
        listOfStoreBags.add(reusableBagSmall);
        Bag reusableBagMedium = new Bag("Medium Reusable Bag", new BigDecimal("0.50"), 0.2);
        listOfStoreBags.add(reusableBagMedium);
        Bag reusableBagLarge = new Bag("Large Reusable Bag", new BigDecimal("1"), 0.3);
        listOfStoreBags.add(reusableBagLarge);
        Bag disposableBagSmall = new Bag("Small Disposable Bag", new BigDecimal("0.25"), 0.1);
        listOfStoreBags.add(disposableBagSmall);
        Bag disposableBagMedium = new Bag("Medium Disposable Bag", new BigDecimal("0.50"), 0.2);
        listOfStoreBags.add(disposableBagMedium);
        Bag disposableBagLarge = new Bag("Large Disposable Bag", new BigDecimal("1"), 0.3);
        listOfStoreBags.add(disposableBagLarge);
    }

    public void purchaseBag(Bag typeofBag) throws SimulationException {

        if (typeofBag == null ) {
            throw new SimulationException("Please select a valid bag type.");
        }
        for(Bag bag : listOfStoreBags) {
            if(bag.getWeight() == typeofBag.getWeight() && bag.getPrice().equals(typeofBag.getPrice()) && bag.getDescription().equals(typeofBag.getDescription())) {
                addBag(bag);
                return;
            }
        }
        throw new SimulationException("Please select a valid bag type.");
    }

    public void addOwnBag(double bagWeight) {
        Bag ownBag = new Bag("Own Bag", new BigDecimal(0.000001), bagWeight);
        addBag(ownBag);
        PurchasedItems.addAmountPaid(ownBag.getPrice()); // This is done to avoid the exception thrown by the addBag method as the price cannot be zero
    }
}
