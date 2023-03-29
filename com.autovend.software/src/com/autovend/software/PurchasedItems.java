package com.autovend.software;

import com.autovend.products.BarcodedProduct;

import java.math.BigDecimal;
import java.util.ArrayList;


public class PurchasedItems{

    private static ArrayList<BarcodedProduct> listOfProducts;
    private static BigDecimal totalPrice;
    private static double totalExpectedWeight;
    private static BigDecimal change;
    private static BigDecimal amountPaid;

    static {
        listOfProducts = new ArrayList<>();
        totalPrice = new BigDecimal(0);
        amountPaid = new BigDecimal(0);
        totalExpectedWeight = 0;
        change = new BigDecimal(0);
    }

    public static void addProduct(BarcodedProduct product){
        listOfProducts.add(product);
        totalPrice = totalPrice.add(product.getPrice());
        totalExpectedWeight += product.getExpectedWeight();
    }

    public static ArrayList<BarcodedProduct> getListOfProducts(){
        return listOfProducts;
    }

    // I think this is not necessary for this iteration but will be useful for future ones
    public static void removeProduct(BarcodedProduct product){
        listOfProducts.remove(product);
        totalPrice = totalPrice.subtract(product.getPrice());
        totalExpectedWeight -= product.getExpectedWeight();
    }

    public static BigDecimal getTotalPrice(){
        return totalPrice;
    }

    public static double getTotalExpectedWeight(){
        return totalExpectedWeight;
    }

    public static void setChange(BigDecimal amount){
        change = amount;
    }

    public static BigDecimal getChange(){
        return change;
    }


    public static void addAmountPaid(BigDecimal amount) {
        amountPaid.add(amount);
    }

    public static BigDecimal getAmountPaid(){
        return amountPaid;
    }

    public static BigDecimal getAmountLeftToPay() {
    	return totalPrice.subtract(amountPaid);
    }
}
