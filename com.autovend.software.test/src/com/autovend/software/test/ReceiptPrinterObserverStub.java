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
package com.autovend.software.test;

import com.autovend.devices.AbstractDevice;
import com.autovend.devices.ReceiptPrinter;
import com.autovend.devices.observers.AbstractDeviceObserver;
import com.autovend.devices.observers.ReceiptPrinterObserver;

public class ReceiptPrinterObserverStub implements ReceiptPrinterObserver {
    private boolean hasInk;
    private boolean hasPaper;

    @Override
    public void reactToEnabledEvent(AbstractDevice<? extends AbstractDeviceObserver> device) {}

    @Override
    public void reactToDisabledEvent(AbstractDevice<? extends AbstractDeviceObserver> device) {}

    @Override
    public void reactToOutOfPaperEvent(ReceiptPrinter printer) { hasPaper = false; }

    @Override
    public void reactToOutOfInkEvent(ReceiptPrinter printer) { hasInk = false; }

    @Override
    public void reactToPaperAddedEvent(ReceiptPrinter printer) { hasPaper = true; }

    @Override
    public void reactToInkAddedEvent(ReceiptPrinter printer) { hasInk = true; }

    public boolean hasPaper() { return hasPaper; }

    public boolean hasInk() { return hasInk; }
}
