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

import java.math.BigDecimal;

import com.autovend.devices.AbstractDevice;
import com.autovend.devices.SelfCheckoutStation;
import com.autovend.devices.SimulationException;
import com.autovend.software.observers.PayObserver;


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

    protected void pay(BigDecimal amountToPay) {
        PurchasedItems.addAmountPaid(amountToPay);
        BigDecimal amountPaid = PurchasedItems.getAmountPaid();
    	if (amountPaid.equals(amountDue)) {
    		for (PayObserver observer : observers) {
    			observer.reactToSufficientPaymentEvent(this);
    		}
    	}
    }

    public BigDecimal getAmountDue() {
        return PurchasedItems.getAmountLeftToPay();
    }
}
