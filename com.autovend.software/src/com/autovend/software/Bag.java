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

import com.autovend.products.Product;

public class Bag extends Product {
	private final String description;
	private final double bagWeight;
	public static BigDecimal price = new BigDecimal("0.25");

	public Bag(String description, BigDecimal price, double expectedWeight) {
		super(price, true);
		if (price == null || expectedWeight <= 0 || description == null){
			throw new IllegalArgumentException("Parameters cannot be null");
		}
		this.bagWeight = expectedWeight;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public double getWeight() {
		return bagWeight;
	}


}



