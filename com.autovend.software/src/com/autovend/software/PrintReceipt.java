

package com.autovend.software;
import com.autovend.devices.*;
import com.autovend.products.BarcodedProduct;

import java.util.ArrayList;

public class PrintReceipt {

    /**
     * This method prints the receipt for the customer. It is executed after the customer has paid for the items.
     * TODO: Get a signal from the payment system that the customer has paid.
     * TODO: Create handles for the exceptions that may be thrown by the printer.
     */

    private String receipt;
    private final ReceiptPrinterObserverStub rpo = new ReceiptPrinterObserverStub();


    public void printReceipt(SelfCheckoutStation station, PurchasedItems purchasedItems) throws OverloadException {
        station.printer.register(rpo);
        ArrayList<BarcodedProduct> items = purchasedItems.getListOfProducts();

        // Give the receipt a title
        String receiptTitle = String.format("%23s\n", "-----RECEIPT-----") + String.format("%-9s %20s\n", "ITEMS", "PRICE");

        // Body of the receipt where the items and prices are included
        StringBuilder receiptItems = new StringBuilder();

        // Iterates through the ArrayList and adds the items and their price to the receipt
        for (BarcodedProduct item : items){
            String price = item.getPrice().toString();
            String description = item.getDescription();
            receiptItems.append(String.format("%-10s %18s$\n", description, price));
        }

        // End of the receipt, includes the total and change
        String receiptChangeAndTotal = String.format("\n%-10s %18s$\n", "TOTAL:", purchasedItems.getTotalPrice().toString()) +
                String.format("%-10s %17s$", "Change Due:", purchasedItems.getChange().toString());

        StringBuilder finalReceipt = new StringBuilder();
        finalReceipt.append(receiptTitle).append(receiptItems).append(receiptChangeAndTotal);
        try {
            // Print the receipt content, if it runs out of ink or paper then disable the printer
            for (int i = 0; i < finalReceipt.length(); i++) {
                // These should notify an attendant and disable the printer
                if (!rpo.hasPaper()) { station.printer.disable(); }
                if (!rpo.hasInk()) { station.printer.disable(); }
                station.printer.print(finalReceipt.charAt(i));
            }
        } catch (EmptyException e) {
            // Disable the printer and notify attendant
            station.printer.disable();
        }

        station.printer.cutPaper();
        receipt = station.printer.removeReceipt();
    }

    public String getReceipt() { return receipt; }
}