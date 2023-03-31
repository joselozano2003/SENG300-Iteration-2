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

import java.util.Scanner;


public class Membership {

    //Membership number needs to be a string of length 10 and should only have digits from 0 to 9
    //this is the criteria for membership number to be considered valid
    public static void isValid(String membershipNumber) throws Exception {
    	if (membershipNumber.length() != 10) {
    		throw new Exception("Membership Number should be of 10 digits long");
    	}
        for (char c : membershipNumber.toCharArray()) {
            if(c < '0' || c > '9') {
                throw new Exception("Membership number should only contain digits from 0 to 9.");
            }
        }

    }
    
  }
    
