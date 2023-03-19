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


    @Override
    public void reactToEnabledEvent(AbstractDevice<? extends AbstractDeviceObserver> device) {

    }

    @Override
    public void reactToDisabledEvent(AbstractDevice<? extends AbstractDeviceObserver> device) {

    }

    @Override
    public void reactToBarcodeScannedEvent(BarcodeScanner barcodeScanner, Barcode barcode) {

    }

    @Override
    public void reactToWeightChangedEvent(ElectronicScale scale, double weightInGrams) {

    }

    @Override
    public void reactToOverloadEvent(ElectronicScale scale) {

    }

    @Override
    public void reactToOutOfOverloadEvent(ElectronicScale scale) {

    }
}
