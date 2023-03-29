

package com.autovend.software;

import java.math.BigDecimal;

import com.autovend.devices.AbstractDevice;
import com.autovend.devices.SelfCheckoutStation;
import com.autovend.devices.SimulationException;


public abstract class Pay extends AbstractDevice<PayObserver> {
    protected SelfCheckoutStation station;
    protected BigDecimal amountDue;


    public Pay(SelfCheckoutStation station) {
        if (station == null) {
            throw new SimulationException(new NullPointerException("Station cannot be null."));
        }
        this.station = station;
        amountDue = PurchasedItems.getAmountLeftToPay();
    }

    /**
     * Updates amountPaid and creates a reactToSufficientPaymentEvent if payment is sufficient to cover the bill
     * @param amountToPay
     */
    protected void Pay(BigDecimal amountToPay) {
        PurchasedItems.addAmountPaid(amountToPay);
        BigDecimal amountPaid = PurchasedItems.getAmountPaid();
    	if (amountPaid.compareTo(amountDue) >= 0) {
    		for (PayObserver observer : observers) {
    			observer.reactToSufficientPaymentEvent(this);
    		}
    	}
    }

    public BigDecimal getAmountDue() {
        return amountDue;
    }
}
