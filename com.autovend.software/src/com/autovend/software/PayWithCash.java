package com.autovend.software;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

import com.autovend.Bill;
import com.autovend.devices.AbstractDevice;
import com.autovend.devices.BillDispenser;
import com.autovend.devices.BillSlot;
import com.autovend.devices.BillValidator;
import com.autovend.devices.DisabledException;
import com.autovend.devices.EmptyException;
import com.autovend.devices.OverloadException;
import com.autovend.devices.SelfCheckoutStation;
import com.autovend.devices.observers.AbstractDeviceObserver;
import com.autovend.devices.observers.BillDispenserObserver;
import com.autovend.devices.observers.BillValidatorObserver;

/**
 * Class that allows customer to pay with cash.
 *
 *
 */
public class PayWithCash extends Pay implements BillDispenserObserver, BillValidatorObserver {


    public PayWithCash(SelfCheckoutStation station, PurchasedItems items) {
        super(station, items);
    }

    @Override
    public void reactToEnabledEvent(AbstractDevice<? extends AbstractDeviceObserver> device) {

    }

    @Override
    public void reactToDisabledEvent(AbstractDevice<? extends AbstractDeviceObserver> device) {

    }

    @Override
    public void reactToBillsFullEvent(BillDispenser dispenser) {

    }

    @Override
    public void reactToBillsEmptyEvent(BillDispenser dispenser) {

    }

    @Override
    public void reactToBillAddedEvent(BillDispenser dispenser, Bill bill) {

    }

    @Override
    public void reactToBillRemovedEvent(BillDispenser dispenser, Bill bill) {

    }

    @Override
    public void reactToBillsLoadedEvent(BillDispenser dispenser, Bill... bills) {

    }

    @Override
    public void reactToBillsUnloadedEvent(BillDispenser dispenser, Bill... bills) {

    }

    @Override
    public void reactToValidBillDetectedEvent(BillValidator validator, Currency currency, int value) {

    }

    @Override
    public void reactToInvalidBillDetectedEvent(BillValidator validator) {

    }

    @Override
    void pay() {

    }

    @Override
    void finishPay() {

    }
}
