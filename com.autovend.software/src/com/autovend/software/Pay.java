

package com.autovend.software;

import java.math.BigDecimal;

import com.autovend.devices.AbstractDevice;
import com.autovend.devices.SelfCheckoutStation;
import com.autovend.devices.SimulationException;

public abstract class Pay extends AbstractDevice<PayObserver> {
    protected SelfCheckoutStation station;

    protected PurchasedItems items;
    protected BigDecimal amountDue;
    protected BigDecimal amountPaid;


    public Pay(SelfCheckoutStation station, PurchasedItems items) {
        if (station == null || items == null) {
            throw new SimulationException(new NullPointerException("No argument may be null."));
        }

        this.station = station;
        this.items = items;

        // initialize fields
        amountDue = new BigDecimal(0);
        amountPaid = new BigDecimal(0);
    }


    abstract void pay();

    abstract void finishPay();

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }
}
