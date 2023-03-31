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

import com.autovend.products.BarcodedProduct;
import com.autovend.products.Product;

import java.math.BigDecimal;
import java.util.ArrayList;


public class PurchasedItems{

    private static ArrayList<BarcodedProduct> listOfProducts;
    private static ArrayList<Product> listOfBags;
    private static BigDecimal totalPrice;
    private static double totalExpectedWeight;
    private static BigDecimal change;
    private static BigDecimal amountPaid;
    private static boolean isPaid;

    static {
        listOfProducts = new ArrayList<>();
        listOfBags = new ArrayList<>();
        totalPrice = new BigDecimal(0);
        amountPaid = new BigDecimal(0);
        totalExpectedWeight = 0;
        change = new BigDecimal(0);
        isPaid = false;
    }

    public static void addProduct(BarcodedProduct product){
        listOfProducts.add(product);
        totalPrice = totalPrice.add(product.getPrice());
        totalExpectedWeight += product.getExpectedWeight();
        if (totalPrice.compareTo(amountPaid) >= 0) {
            isPaid = false;
        }
    }

    public static void addBag(Bag bag){
        listOfBags.add(bag);
        totalPrice = totalPrice.add(bag.getPrice());
        totalExpectedWeight += bag.getWeight();
        if (totalPrice.compareTo(amountPaid) >= 0) {
            isPaid = false;
        }
    }

    public static ArrayList<BarcodedProduct> getListOfProducts(){
        return listOfProducts;
    }

    public static ArrayList<Product> getListOfBags(){
        return listOfBags;
    }

    // I think this is not necessary for this iteration but will be useful for future ones
    public static void removeProduct(BarcodedProduct product){
        listOfProducts.remove(product);
        totalPrice = totalPrice.subtract(product.getPrice());
        totalExpectedWeight -= product.getExpectedWeight();
    }

    public static void removeOtherProduct(Product product, double weight){
        listOfBags.remove(product);
        totalPrice = totalPrice.subtract(product.getPrice());
        totalExpectedWeight -= weight;
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
        amountPaid = amountPaid.add(amount);
        if (amountPaid.compareTo(totalPrice) >= 0) {
        	isPaid = true;
        }
    }

    public static boolean isPaid() {
    	return isPaid;
    }

    public static BigDecimal getAmountPaid(){
        return amountPaid;
    }

    public static BigDecimal getAmountLeftToPay() {
    	return totalPrice.subtract(amountPaid);
    }


    // This method is used for testing purposes ONLY
    public static void setAmountPaid(BigDecimal amount) {
    	amountPaid = amount;
        if (amountPaid.compareTo(totalPrice) >= 0) {
            isPaid = true;
        }
    }

    public static void reset() {
    	listOfProducts = new ArrayList<>();
        listOfBags = new ArrayList<>();
        totalPrice = new BigDecimal(0);
        amountPaid = new BigDecimal(0);
        totalExpectedWeight = 0;
        change = new BigDecimal(0);
        isPaid = false;
    }
}
