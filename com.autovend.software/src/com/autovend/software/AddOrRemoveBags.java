package com.autovend.software;

import java.math.*;
import java.util.ArrayList;

import com.autovend.devices.*;


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
        if (typeofBag == null || !listOfStoreBags.contains(typeofBag)) {
            throw new SimulationException("Please select a valid bag type.");
        }
        else {
            addBag(typeofBag);
        }
    }

    public void addOwnBag(double bagWeight) {
        Bag ownBag = new Bag("Own Bag", new BigDecimal(0), bagWeight);
        addBag(ownBag);
    }
}
