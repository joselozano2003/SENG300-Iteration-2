

package com.autovend.software;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import com.autovend.Barcode;
import com.autovend.BarcodedUnit;
import com.autovend.devices.AbstractDevice;
import com.autovend.devices.BarcodeScanner;
import com.autovend.devices.ElectronicScale;
import com.autovend.devices.OverloadException;
import com.autovend.devices.SelfCheckoutStation;
import com.autovend.devices.observers.AbstractDeviceObserver;
import com.autovend.devices.observers.BarcodeScannerObserver;
import com.autovend.devices.observers.ElectronicScaleObserver;
import com.autovend.external.ProductDatabases;
import com.autovend.products.BarcodedProduct;

public class ScanItems implements BarcodeScannerObserver, ElectronicScaleObserver{

    private SelfCheckoutStation selfCheckoutStation;
    private PurchasedItems purchaseList;

    /*
     * Constructor for ScanItems
     */
    public ScanItems(SelfCheckoutStation station, PurchasedItems purchasedItems) {
        this.selfCheckoutStation = station;
        this.purchaseList = purchasedItems;
    }

    public PurchasedItems getPurchasedItems() {
        return this.purchaseList;
    }

    /*
     *  Performs actions after item has been scanned
     *
     */
    @Override
    public void reactToBarcodeScannedEvent(BarcodeScanner barcodeScanner, Barcode barcode) {

        // 2. Blocks the self-checkout station from further customer interaction
        barcodeScanner.disable();
        selfCheckoutStation.handheldScanner.disable();
        selfCheckoutStation.mainScanner.disable();

        // 3. System: Determines the characteristics (weight and cost) of the product associated with the given barcode.
        // 4. System: Updates the expected weight from the Bagging Area.
        BarcodedProduct itemProduct = ProductDatabases.BARCODED_PRODUCT_DATABASE.get(barcode);
        double itemWeight = itemProduct.getExpectedWeight();
        purchaseList.addProduct(itemProduct);

        // 5. System: Signals to the Customer I/O to place the scanned item in the Bagging Area.
        BarcodedUnit scannedUnit = new BarcodedUnit(barcode, itemWeight);
        CustomerIOStub.itemHasBeenScannedEvent(this.selfCheckoutStation, scannedUnit);

    }

    /*
     *  Weight changed in bagging area. Compares the total weight of the products that have been scanned
     *  with the weight of the bagging area to confirm whether it is accurate
     *
     *  If they match, user can continue scanning, if not, scanner is disabled and other
     *  actions are needed.
     */

    // 6. Signals to the system that the weight has changed.
    @Override
    public void reactToWeightChangedEvent(ElectronicScale scale, double weightInGrams) {
        // Exception 1. Weight Discrepancy
        // stays disabled if the weight of the bagging area does not match the expected weight
        try {
            if(purchaseList.getTotalExpectedWeight() == selfCheckoutStation.baggingArea.getCurrentWeight()) {
                selfCheckoutStation.handheldScanner.enable();
                selfCheckoutStation.mainScanner.enable();
            }

        } catch (OverloadException e) {
            // TODO Auto-generated catch block
        }

    }

    @Override
    public void reactToEnabledEvent(AbstractDevice<? extends AbstractDeviceObserver> device) {
        // TODO Auto-generated method stub

    }
    @Override
    public void reactToDisabledEvent(AbstractDevice<? extends AbstractDeviceObserver> device) {
        // TODO Auto-generated method stub

    }

    @Override
    public void reactToOverloadEvent(ElectronicScale scale) {
        // TODO Auto-generated method stub

    }

    @Override
    public void reactToOutOfOverloadEvent(ElectronicScale scale) {
        // TODO Auto-generated method stub

    }

}