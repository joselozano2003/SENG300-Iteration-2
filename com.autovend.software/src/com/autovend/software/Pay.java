

package com.autovend.software;

import java.math.BigDecimal;

import com.autovend.devices.AbstractDevice;
import com.autovend.devices.SelfCheckoutStation;
import com.autovend.devices.SimulationException;

public abstract class Pay extends AbstractDevice<PayObserver> {
    protected SelfCheckoutStation station;

    protected BigDecimal amountDue;
    protected BigDecimal amountPaid;


    public Pay(SelfCheckoutStation station, PurchasedItems items) {
        if (station == null || items == null) {
            throw new SimulationException(new NullPointerException("No argument may be null."));
        }

        this.station = station;
        amountDue = items.getTotalPrice();
        amountPaid = new BigDecimal(0); // Add method

    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }
}
